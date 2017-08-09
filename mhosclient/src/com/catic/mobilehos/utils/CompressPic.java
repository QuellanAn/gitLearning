package com.catic.mobilehos.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CompressPic {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private final int DEFAULT_LIMIT_SIZE = 64 * 1024;
	
	private int outputWidth;
	
	private int outputHeight;
	
	private boolean proportion;
	
	private int limitSize = DEFAULT_LIMIT_SIZE;
	
	
	
	
	public int getOutputWidth() {
		return outputWidth;
	}


	public void setOutputWidth(int outputWidth) {
		this.outputWidth = outputWidth;
	}

	public int getOutputHeight() {
		return outputHeight;
	}


	public void setOutputHeight(int outputHeight) {
		this.outputHeight = outputHeight;
	}

	public boolean isProportion() {
		return proportion;
	}


	public void setProportion(boolean proportion) {
		this.proportion = proportion;
	}
	
	public int getLimitSize() {
		return limitSize;
	}

	public void setLimitSize(int limitSize) {
		this.limitSize = limitSize;
	}


	public boolean compress(File srcFile, String outputPath){
        try{
           byte[] imgData = this.compress(srcFile);
           if (imgData != null && imgData.length > 0){
        	   FileUtils.writeByteArrayToFile(new File(outputPath), imgData);
        	   return true;
           }else{
        	   return false;
           }
        }catch(Exception e){
        	log.error(null, e);
        	return false;
        }
	}
	
	
	public byte[] compress(File srcFile) {
		try {
			InputStream is = new FileInputStream(srcFile);
			return this.compress(is);
		} catch (Exception e) {
			log.error(null, e);
			return null;
		}

	}
	
	
	public byte[] compress(InputStream is) {
		try {
			byte[] imgDataBuf = IOUtils.toByteArray(is);
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(imgDataBuf));
			// 判断图片格式是否正确
			if (img.getWidth(null) == -1) {
				log.warn("图片无法读取，可能格式不支持！");
				return null;
			} else if (imgDataBuf.length < limitSize 
					&& img.getWidth(null) <= this.outputWidth 
					&& img.getHeight(null) <= this.outputHeight
					){
				log.info("图片大小小于指定大小，不需要压缩。");
				return imgDataBuf;
			} else {
				int newWidth;
				int newHeight;
				if (proportion == true) {
					// 为等比缩放计算输出的图片宽度及高度
					double rate1 = ((double) img.getWidth(null))
							/ (double) outputWidth + 0.1;
					double rate2 = ((double) img.getHeight(null))
							/ (double) outputHeight + 0.1;
					// 根据缩放比率大的进行缩放控制
					double rate = rate1 > rate2 ? rate1 : rate2;
					newWidth = (int) (((double) img.getWidth(null)) / rate);
					newHeight = (int) (((double) img.getHeight(null)) / rate);
				} else {
					newWidth = outputWidth; // 输出的图片宽度
					newHeight = outputHeight; // 输出的图片高度
				}
				BufferedImage bufImg = new BufferedImage((int) newWidth,
						(int) newHeight, BufferedImage.TYPE_INT_RGB);

				/*
				 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
				 */
				bufImg.getGraphics().drawImage(
						img.getScaledInstance(newWidth, newHeight,
								Image.SCALE_SMOOTH), 0, 0, null);
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				
				// JPEGImageEncoder可适用于其他图片类型的转换
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(bufImg);
				byte[] imgData = out.toByteArray();
				log.debug("压缩后的图片大小：" + imgData.length);
				out.close();

				return imgData;
			}
		} catch (Exception e) {
			System.out.println("compress result is : " + e);
			log.error(null, e);
			return null;

		}

	}

	
	
	
	
	

}
