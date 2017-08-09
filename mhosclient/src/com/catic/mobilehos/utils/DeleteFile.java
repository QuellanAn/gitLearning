package com.catic.mobilehos.utils;

import java.io.File;

public class DeleteFile {
	//private String path = "E:/aa.xls";
	// 验证字符串是否为正确路径名的正则表达式  
	private static String matches = "[A-Za-z]:\\\\[^:?\"><*]*";  
	// 通过 sPath.matches(matches) 方法的返回值判断是否正确  
	// sPath 为路径字符串  
	
	private static File file = null;
	private static boolean flag = true;
	
	/**
	 * 判断文件路径是否正确
	 * @param path
	 * @return
	 */
	public static boolean matchPath(String path){
		flag = false;
		if(path.matches(matches)){
			flag = true; 
		}
		return flag;
	}
	
	/** 
	 *  根据路径删除指定的目录或文件，无论存在与否 
	 *@param sPath  要删除的目录或文件 
	 *@return 删除成功返回 true，否则返回 false。 
	 */  
	public boolean DeleteFolder(String path) {  
	    flag = false;  
	    file = new File(path);  
	    // 判断目录或文件是否存在  
	    if (!file.exists()) {  // 不存在返回 false  
	        return flag;  
	    } else {  
	        // 判断是否为文件  
	        if (file.isFile()) {  // 为文件时调用删除文件方法  
	            return deleteFile(path);  
	        } else {  // 为目录时调用删除目录方法  
	            return deleteDirectory(path);  
	        }  
	    }  
	}  
	
	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public static boolean deleteFile(String path) {  
	    flag = false;  
	    file = new File(path);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}  
	
	/** 
	 * 删除目录（文件夹）以及目录下的文件 
	 * @param   sPath 被删除目录的文件路径 
	 * @return  目录删除成功返回true，否则返回false 
	 */  
	public static boolean deleteDirectory(String path) {  
	    //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
	    if (!path.endsWith(File.separator)) {  
	        path = path + File.separator;  
	    }  
	    File dirFile = new File(path);  
	    //如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        return false;  
	    }  
	    flag = true;  
	    //删除文件夹下的所有文件(包括子目录)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //删除子文件  
	        if (files[i].isFile()) {  
	            flag = deleteFile(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        } //删除子目录  
	        else {  
	            flag = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        }  
	    }  
	    if (!flag) return false;  
	    //删除当前目录  
	    if (dirFile.delete()) {  
	        return true;  
	    } else {  
	        return false;  
	    }  
	}  
	
	/*public static void main(String[] args) {  
		  
	   //HandleFileClass hfc = new HandleFileClass();  
	    String path = "D:\\Abc\\123\\Ab1";  
	    //boolean result = hfc.CreateFolder(path);  
	    //System.out.println(result);  
	    path = "D:\\Abc\\124";  
	    //result = hfc.DeleteFolder(path);  
	    //System.out.println(result);  
	  
	}  */
}
