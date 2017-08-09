package com.catic.mobilehos.action;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

import com.catic.mobilehos.GlobalConstants;
import com.catic.mobilehos.service.ServiceResult;

/**
 * 所有使用kingdEditor上传图片的action从该类继承
 * @author xieweipeng
 *
 */
public abstract class KindEditorAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private final int DEF_FILESIZE = 1024;
	
	private final String DEF_EXT = "gif,jpg,jpeg,png,bmp";
	
	

	private Log log = LogFactory.getLog(this.getClass());
	
	

	// 保存的绝对路径
	private String savePath;


	// 允许的后缀
	private String ext = DEF_EXT;
	
	/**
	 * 文件大小限制
	 */
	private int fileSize = DEF_FILESIZE;
	

	HashMap<String, String> extMap = new HashMap<String, String>();
	

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getSaveUrl() {
		return request.getContextPath();
	}

	public String getSavePath() {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}

	
	private String getFileExtName(String fileName){
		int i = fileName.lastIndexOf(".");
		if (i <=0 ){
			return "";
		}else{
			String fileExt = fileName.substring(i + 1).toLowerCase();
			return fileExt;
		}
	}
	
	/**
	 * 继承类实现该方法，处理或保存上传的文件，
	 * 如果成功应该通过（getObject)返回文件URL，失败返回错误信息
	 * @param file
	 * @return
	 */
	protected abstract ServiceResult processFile(File file);
	

	@Override
	public String execute() throws Exception {
		try {
			MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
			response.setCharacterEncoding(GlobalConstants.DEF_ENCODE);

			String fileName = wrapper.getFileNames("imgFile")[0];
			File file = wrapper.getFiles("imgFile")[0];
			if (file.length() > 1024 * fileSize) {
				this.writeError(response, "图片文件大小超过限制！限制大小为：" + fileSize + "KB");
				return null;
			}

			String fileExtName = getFileExtName(fileName);
			if (!Arrays.<String> asList(getExt().split(",")).contains(
					fileExtName)) {
				this.writeError(response, "上传文件扩展名是不允许的扩展名。\n只允许" + getExt()
						+ "格式。");
				return null;
			}

			ServiceResult sr = processFile(file);

			if (sr.isSuccess()) {
				writeUrl(response, (String) sr.getObject());
			} else {
				writeError(response, sr.getErrMsg());
			}
			
			return null;
		} catch (Exception e) {
			log.error(null, e);
			writeError(response, "上传文件失败！");
			return null;
		}
	}
		
	
	private void writeError(HttpServletResponse response, String errMsg){
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", errMsg);
		try {
			log.debug(obj.toString());
			response.getWriter().println(obj.toString());
		} catch (IOException e) {
			log.error(null, e);
		}
	}
	
	private void writeUrl(HttpServletResponse response, String url){
		JSONObject obj = new JSONObject();
		obj.put("error", 0);
		obj.put("url", url);
		try {
			log.debug(obj.toString());
			response.getWriter().println(obj.toString());
		} catch (IOException e) {
			log.error(null, e);
		}
	}


}
