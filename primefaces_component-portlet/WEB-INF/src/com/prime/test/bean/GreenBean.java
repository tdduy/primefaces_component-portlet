//Lý Thái Bảo
package com.prime.test.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "GreenBean")
@ViewScoped
public class GreenBean implements Serializable {
	private static final long serialVersionUID = 2802078677405999646L;
	private String helloWord = "Welcome to Primeface";

	public String getHelloWord() {
		return helloWord;
	}

	public void setHelloWord(String helloWord) {
		this.helloWord = helloWord;
	}
}
