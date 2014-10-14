package com.prime.test.bean.ltbao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "PrimeUploadBean")
@ViewScoped
public class PrimeUploadBean implements Serializable {
	private static final long 						serialVersionUID = -7218166086798564624L;
	private static final Logger						log				 = LoggerFactory.getLogger(PrimeUploadBean.class);
	private 			 List<Map<String,Object>>	listFile		 = new ArrayList<Map<String,Object>>();	
	public PrimeUploadBean(){
		log.info(">> init PrimeUploadBean");
	}
	
	public void actionUpload(FileUploadEvent evt){
		log.info(">> Begin upload");		
	}

	public List<Map<String, Object>> getListFile() {
		return listFile;
	}

	public void setListFile(List<Map<String, Object>> listFile) {
		this.listFile = listFile;
	}
}
