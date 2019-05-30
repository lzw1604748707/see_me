package com.anshuye.common.kit;

import java.io.File;
import java.io.IOException;

import com.anshuye.common.constant.Constant;
import com.jfinal.kit.LogKit;
import com.jfinal.kit.PathKit;
import com.xiaoleilu.hutool.io.FileUtil;

public class UploadFileMoveKit {
	
	/**
	 * @param fileUrl temp目录文件url
	 * @param moveToFoloder 要移动的文件夹
	 * @return 返回相对路径 /upload/folder.../xxx.jpg
	 */
	public static String move(String fileUrl,String moveToFoloder) {
		if (fileUrl.contains("temp")) {
			String fileName = fileUrl.substring(fileUrl.lastIndexOf("/"),fileUrl.length());
			String filePath = Constant.BASE_UPLOAD_PATH + "/"  + fileName;
			String newFilePath =  PathKit.getWebRootPath() + moveToFoloder + fileName;
			try {
				FileUtil.move(new File(filePath), new File(newFilePath), true);
			} catch (IOException e) {
				LogKit.error(e.toString(), e);
			}
			return  moveToFoloder + fileName;
		}
		return fileUrl;
	}

}
