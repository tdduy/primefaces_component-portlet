package com.prime.test.bean.ltbao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

@ManagedBean(name="RedBean")
@ViewScoped
public class PrimeChartBean {
	private PieChartModel pieModel1 = new PieChartModel();
	public PrimeChartBean(){
		pieModel1 = new PieChartModel();
        
        pieModel1.set("Brand 1", 540);
        pieModel1.set("Brand 2", 325);
        pieModel1.set("Brand 3", 702);
        pieModel1.set("Brand 4", 421);
         
        pieModel1.setTitle("Simple Pie");
        pieModel1.setLegendPosition("w");
	}
	
	public void actionWrappedException(){
		throw new NullPointerException();
	}
	
	public PieChartModel getPieModel1() {
		return pieModel1;
	}
	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}
}
