package com.xtsoft.kernel.base;

public class ResponseObject {
	private long totalCount;
	private double costtime;
	private Object data;
	private boolean result;
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public double getCosttime() {
		return costtime;
	}
	public void setCosttime(double costtime) {
		this.costtime = costtime;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
}
