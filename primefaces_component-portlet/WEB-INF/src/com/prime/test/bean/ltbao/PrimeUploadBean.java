package com.prime.test.bean.ltbao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import net.sourceforge.jtds.util.MD5Digest;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "PrimeUploadBean")
@ViewScoped
public class PrimeUploadBean implements Serializable {
	private static final long 						serialVersionUID = -7218166086798564624L;
	private static final Logger						log				 = LoggerFactory.getLogger(PrimeUploadBean.class);
	private 			 List<Map<String,Object>>	listFile		 = new ArrayList<Map<String,Object>>();	
	private 			 String						defaultPath		 = "D:/";
	private 			 Random						random			 = new Random();
	public PrimeUploadBean(){
		log.info(">> init PrimeUploadBean");
	}
	
	/**
	 * @author  ltbao
	 * @purpose Upload file controler
	 * @date    Oct 14, 2014 :: 7:03:00 PM 
	 * @param 	evt
	 */
	public void actionUpload(FileUploadEvent evt){
		try{
			log.info(">> Begin upload");		
			InputStream inputStream = evt.getFile().getInputstream();
			OutputStream outputStream = new FileOutputStream(new File(defaultPath+evt.getFile().getFileName()));			
			int read  = 0;
			byte byteArray[] = new byte[1024];
			while((read = inputStream.read(byteArray))!=-1){
				log.info(">> Buffer len "+read);
				outputStream.write(byteArray);
			}			
			inputStream.close();
			outputStream.flush();
			outputStream.close();
			Map<String,Object> fileMap = new HashMap<String, Object>();
			fileMap.put("fileName", evt.getFile().getFileName());
			listFile.add(fileMap);
			log.info(">> Upload success ");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private String randomName(String cName){
		String re = "";
		for(int i = 0; i < 32 ; i++){
			re = re + random.nextInt(10);
		}
		return "file_"+re+"."+FilenameUtils.getExtension(cName);
	}
	
	/**
	 * @author  ltbao
	 * @purpose Action delete file
	 * @date    Oct 14, 2014 :: 7:19:54 PM 
	 * @param 	evt
	 */
	@SuppressWarnings("unchecked")
	public void actionDeleteFile(AjaxBehaviorEvent evt){
		log.info(">> Begin delete file");
		Object obj = evt.getComponent().getAttributes().get("obj");
		if(obj!=null){
			Map<String,Object> map =(Map<String,Object>)obj;
			File delFile = new File(defaultPath+map.get("fileName").toString());
			log.info(">> Filename " +defaultPath+map.get("fileName").toString());
			if(delFile.exists()){
				log.info(">> Delete file "+defaultPath + map.get("fileName").toString() + " > "+(delFile.delete()?"success":"fail"));
			}
			listFile.remove(obj);
		}
	}
	
	public List<Map<String, Object>> getListFile() {
		return listFile;
	}

	public void setListFile(List<Map<String, Object>> listFile) {
		this.listFile = listFile;
	}
}
