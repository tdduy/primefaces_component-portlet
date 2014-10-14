package com.prime.test.bean.ltbao;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name  = "PrimeLazyLoading")
@ViewScoped
public class PrimeLazyLoading implements Serializable {
	private static final long 			serialVersionUID = -7309728785140444297L;
	private static final Logger			log				 = LoggerFactory.getLogger(PrimeLazyLoading.class);
	private 			 int 			mode			 = 0;
	
	public PrimeLazyLoading(){
		log.info(">> Construct backing bean");
	}
	
	public void initBean1(){
		log.info(">> Beign thread 1");
		try{
			Thread.sleep(5000);
			if(mode!=2){
				mode = mode +1;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	
}
