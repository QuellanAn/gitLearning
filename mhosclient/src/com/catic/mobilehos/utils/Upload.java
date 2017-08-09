package com.catic.mobilehos.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class Upload {

	public Upload() {
	}

	public static void createFileIfNotExist(String filePath) {
		if (filePath != null && !"".equals(filePath)) {
			File file = new File(filePath);
			if (!file.exists())
				file.mkdirs();
		}
	}

	public static void uploadImage(File file, String path, String fileName,
			String fileType) {
		try {
			createFileIfNotExist(path);
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			FileOutputStream fos = new FileOutputStream((new StringBuilder(
					String.valueOf(path))).append(fileName).append(fileType)
					.toString(), true);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			IOUtils.copy(bis, bos);
			bos.flush();
			bos.close();
			bis.close();
			fos.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static long getFileSize(File file) {
		long size = -1L;
		if (file.isFile())
			size = file.length();
		return size;
	}

	public static String getFileName(String path) {
		File file = new File(path);
		String fileName = file.getName();
		return fileName;
	}

	public static String getFileType(String fileFileName) {
		return fileFileName.substring(fileFileName.lastIndexOf("."),
				fileFileName.length());
	}

	public static String getPath(String branch, Integer type) {
		String path = null;
		if (type.intValue() == 0)
			path = (new StringBuilder(String.valueOf(System.getProperty(
					"user.dir").replace("bin", "webapps"))))
					.append(Constant.PATH).append(branch).toString();
		else
			path = (new StringBuilder("C:")).append(Constant.PATH)
					.append(branch).toString();
		return path;
	}

	@SuppressWarnings("unchecked")
	public static String upload(List file, List fileFileName, String branch,
			Integer type) {
		String path = "";
		String uploadPath = null;
		String fileName = null;
		String fileType = null;
		for (int i = 0; i < file.size(); i++) {
			if (file.size() > 1) {
				fileName = Common.getUUID();
				fileType = getFileType((String) fileFileName.get(i));
				uploadPath = getPath(branch, type);
				path = (new StringBuilder(String.valueOf(path)))
						.append(Constant.PATH).append(branch).append(fileName)
						.append(fileType).append(",").toString();
			} else {
				fileName = Common.getUUID();
				fileType = getFileType((String) fileFileName.get(i));
				uploadPath = getPath(branch, type);
				
				path = (new StringBuilder(String.valueOf(Constant.PATH)))
						.append(branch).append(fileName).append(fileType)
						.toString();
			}
			uploadImage((File) file.get(i), uploadPath, fileName, fileType);
		}

		return path;
	}
	
	public static void uploadCheck(File file, String path, String fileName) {
		try {
			createFileIfNotExist(path);
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			FileOutputStream fos = new FileOutputStream((new StringBuilder(
					String.valueOf(path))).append(fileName)
					.toString(), true);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			IOUtils.copy(bis, bos);
			bos.flush();
			bos.close();
			bis.close();
			fos.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
