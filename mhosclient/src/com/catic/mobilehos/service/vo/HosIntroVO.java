package com.catic.mobilehos.service.vo;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.catic.mobilehos.po.HosIntroInfoPO;
import com.catic.mobilehos.utils.CompressPic;

public class HosIntroVO extends HosIntroInfoPO {

	private static final int HOS_IMG_HEIGHT = 300;

	private static final int HOS_IMG_WIDTH = 400;

	private String picContentType;

	private List<ContactsVO> contacts;

	private File pic;

	private String picFileName;

	public List<ContactsVO> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactsVO> contacts) {
		this.contacts = contacts;
	}

	public static class ContactsVO extends ContactsPO {

	}

	public InputStream getPictureStream() {
		return new ByteArrayInputStream(this.getPicture());
	}

	public String getPicContentType() {
		return picContentType;
	}

	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public String getContactsDesc() {
		StringBuilder sb = new StringBuilder();
		for (ContactsVO v : contacts) {
			sb.append(v.getName());
			sb.append(":");
			sb.append(v.getPhone());
			sb.append(" ");
		}
		return sb.toString();
	}

	public boolean compressPic() {
		if (this.getPic() == null) {
			return true;
		}
		CompressPic compress = new CompressPic();
		compress.setOutputHeight(HOS_IMG_HEIGHT);
		compress.setOutputWidth(HOS_IMG_WIDTH);
		compress.setProportion(true);
		byte[] fileData = compress.compress(this.getPic());
		if (fileData == null) {
			return false;
		} else {
			this.setPicture(fileData);
			return true;
		}
	}

}
