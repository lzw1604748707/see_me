package com.sandu.admin.user;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.jfinal.kit.HashKit;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.sandu.common.kit.RetKit;
import com.sandu.common.model.SmPhotoCollection;
import com.sandu.common.model.SmPhotoProject;

public class PhotoCollectionListService {

	public static final	PhotoCollectionListService me = new PhotoCollectionListService();
	static final SmPhotoCollection dao = SmPhotoCollection.dao;
	static final SmPhotoProject projectDao = SmPhotoProject.dao;
	
	public Page<SmPhotoCollection> list(int pageNumber, int pageSize, Kv kv) {
		Page<SmPhotoCollection> page = dao.paginate(pageNumber, pageSize, dao.getSqlPara("sm_photo_collection.admin-paginate", kv));
		return page;
	}
	
	public SmPhotoCollection findById(Integer id){
		return dao.findById(id);
	}
	//上架or下架
	public RetKit changeStatus(Integer id,String status) {
		SmPhotoCollection photoCollection = findById(id);
		boolean a=Integer.parseInt(status)==1;
		boolean success = 	photoCollection.setStatus(Integer.parseInt(status)==1).update();
		return success ? RetKit.ok() : RetKit.fail();
	}
	public RetKit save(SmPhotoCollection collection,String projectIds){
		if (collection == null ) {
			return RetKit.fail("非法参数");
		}
		collection.setCreateDate(new Date());
		collection.setUpdateDate(new Date());
		boolean success = collection.save();
		int collectionId = collection.getId();
		String[] projectIdList= projectIds.split(",");
		int length=projectIdList.length;
		for (int i=0;i<length;i++) {
			Db.update("insert into sm_photo_collection_record(collectionId,projectId,createDate) values(?,?,?)",collectionId,Integer.parseInt(projectIdList[i]),new Date());
		}
		return success ? RetKit.ok() : RetKit.fail();
	}
	
	public RetKit update(SmPhotoCollection collection,String projectIds){
		boolean success = collection.setUpdateDate(new Date()).update();
		int collectionId = collection.getId();
		Db.update("delete FROM sm_photo_collection_record WHERE collectionId=?",collectionId);
		String[] projectIdList= projectIds.split(",");
		int length=projectIdList.length;
		for (int i=0;i<length;i++) {
			Db.update("insert into sm_photo_collection_record(collectionId,projectId,createDate) values(?,?,?)",collectionId,Integer.parseInt(projectIdList[i]),new Date());
		}
		return success ? RetKit.ok() : RetKit.fail();
	}
	public List<SmPhotoProject> findAllProjectToCardList() {
		List<SmPhotoProject> projectList = projectDao.find("select id,title,cover,viewCount,commentCount from sm_photo_project");
		return projectList;
	}
	//逻辑删除
	public RetKit remove(Integer id) {
		SmPhotoCollection removeCollection = findById(id);
		boolean success = removeCollection.setIsDelete("0").update();
		return success ? RetKit.ok() : RetKit.fail();
	}	
}
