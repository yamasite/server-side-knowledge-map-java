package com.javadevmap.elasticjobexample.model;

public class OrderStatis {
	private Double priceTotal;
	private Long count;
	public Double getPriceTotal() {
		return priceTotal;
	}
	public void setPriceTotal(Double priceTotal) {
		this.priceTotal = priceTotal;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "pricetotal = " + priceTotal + " count = " + count;
	}
	
	
}
