package com.sandu.admin.user;

import com.jfinal.kit.Kv;
import com.sandu.common.controller.AdminController;
import com.sandu.common.kit.RetKit;
import com.sandu.common.model.School;
/**
 * 学校列表控制层
 * @ClassName:SchoolListController   
 * @author:Seven
 * @date:2017年12月15日 下午2:54:12
 */
public class SchoolListController extends AdminController {
	
	static final SchoolListService srv = SchoolListService.me;
	
	
	/**
	 * 學校列表
	 */
	public void list(){
		int pageNumber = getParaToInt("pageNumber",1);
		int pageSize = getParaToInt("pageSize",10);
		Kv kv = getParaToMap();
		renderJson(RetKit.ok("page",srv.paginate(pageNumber, pageSize, kv)));
	}

	/**
	 * 導出學校列表
	 */
	public void export(){
		//TODO  導出學校列表需要實現
		renderJson(RetKit.ok("暫未實現，請聯繫開發"));
	}
	
	/**
	 * 刪除
	 */
	public void delete(){
		Integer id = getParaToInt();
		boolean succ = srv.deleteById(id);
		renderJson(succ ? RetKit.ok():RetKit.fail());
	}
	
	/**
	 * 編輯
	 */
	public void update(){
		School school = getBean(School.class,"");
		srv.update(school);
		//不需要判断true false，如果报错，必定走500了。
		renderJson(RetKit.ok());
	}
	
	/**
	 * 保存
	 */
	public void save(){
		School school = getBean(School.class,"");
		renderJson(srv.save(school));
	}
	
	/**
	 * 停用
	 */
	public void stop(){
		Integer id = getParaToInt();
		boolean succ = srv.stopById(id);
		renderJson(succ ? RetKit.ok():RetKit.fail());
	}
	
	/**
	 * 启用
	 */
	public void enable(){
		Integer id = getParaToInt();
		boolean succ = srv.enableById(id);
		renderJson(succ ? RetKit.ok():RetKit.fail());
	}
	
	/**
	 * 重置管理员密码
	 */
	public void resetLeaderPhone(){
		Integer id = getParaToInt();
		renderJson(srv.resetLeaderPhone(id));
	}
	
	
}
