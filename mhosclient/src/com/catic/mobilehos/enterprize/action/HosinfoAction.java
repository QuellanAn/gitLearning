package com.catic.mobilehos.enterprize.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import com.catic.mobilehos.enterprize.entity.Hosinfo;
import com.catic.mobilehos.utils.Page;

@SuppressWarnings("serial")
@Controller
public class HosinfoAction extends BaseAction {
	private String id;
	private String starttime;
	private String endtime;
	private Hosinfo info;
	private List<Hosinfo> list;
	
    private File files;
    private String filesFileName;
    private String filesContentType;
	
	public String findAll(){
		try {
			if(StringUtils.isBlank(pageNo)){
				pageNo="1";
			}
			if(StringUtils.isNotBlank(starttime)){
				info.setStarttime(starttime);
			}
			if(StringUtils.isNotBlank(endtime)){
				info.setEndtime(endtime);
			}
			list=hosinfoBiz.findAll(info,page);
			if(list!=null&&list.size()>0){
				page=new Page(Integer.parseInt(pageNo), 10, list.get(0).getCount());
				list=hosinfoBiz.findAll(info,page);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String toUpdate(){
		try {
			info=hosinfoBiz.getInfo(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "toUpdate";
	}
	
	public String addOrUpdate(){
		try {
			if (files != null) {
				File path=new File(DISK+"/meida/image/new");
		        if  (!path.exists()  && !path.isDirectory())      
		        {       
		            System.out.println("//不存在");  
		            path.mkdir();    
		        } else   
		        {  
		            System.out.println("//目录存在");  
		        }  
	            File savefile = new File(path, filesFileName);
	            if (!savefile.getParentFile().exists())
	                savefile.getParentFile().mkdirs();
	            FileUtils.copyFile(files, savefile);
	        }
			if(info.getId()==null){
				String username=(String) request.getSession().getAttribute("userName");
				info.setAuthor(username);
				info.setImage(DISK+"/meida/image/new/"+filesFileName);
				hosinfoBiz.add(info);
			}else{
				hosinfoBiz.update(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveUpdate";
	}
	
	public String delete(){
		try {
			info=hosinfoBiz.getInfo(Integer.parseInt(id));
			if(StringUtils.isNotBlank(info.getImage())){
				deleteFile(info.getImage());
			}
			hosinfoBiz.delete(Integer.parseInt(id));
//			if(StringUtils.isBlank(pageNo)){
//	          	 pageNo="1";
//	        }         
//			list=hosinfoBiz.findAll(info,page);
//			if(list!=null&&list.size()>0){
//				page=new Page(Integer.parseInt(pageNo), 10, list.get(0).getCount());
//				list=hosinfoBiz.findAll(info,page);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveUpdate";
	}
	
	 /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public void deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
        }
    }
    
    /**
     * 编辑时获取文件流
    * @return
    * @throws IOException
    * 2017-6-14
    * @author lxg
    *
     */
    public String getimg() throws IOException {
    	  String path=request.getParameter("img");
    	  response.reset();
    	  response.setContentType("text/html; charset=UTF-8");
    	  response.setContentType("image/jpeg"); // 设置图片格式格式，这里可以忽略
    	  FileInputStream fis = new FileInputStream(path);
    	  OutputStream os = response.getOutputStream();
    	  try {
    	   int count = 0;
    	   byte[] buffer = new byte[1024 * 1024];
    	   while ((count = fis.read(buffer)) != -1)
    	    os.write(buffer, 0, count);
    	  } catch (IOException e) {
    	   e.printStackTrace();
    	  } finally {
    	   if (os != null)
    	    os.close();
    	   if (fis != null)
    	    fis.close();
    	  }
    	  return null;
    	 }
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Hosinfo getInfo() {
		return info;
	}
	public void setInfo(Hosinfo info) {
		this.info = info;
	}

	public List<Hosinfo> getList() {
		return list;
	}

	public void setList(List<Hosinfo> list) {
		this.list = list;
	}

	public File getFiles() {
		return files;
	}

	public void setFiles(File files) {
		this.files = files;
	}

	public String getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(String filesFileName) {
		this.filesFileName = filesFileName;
	}

	public String getFilesContentType() {
		return filesContentType;
	}

	public void setFilesContentType(String filesContentType) {
		this.filesContentType = filesContentType;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
}