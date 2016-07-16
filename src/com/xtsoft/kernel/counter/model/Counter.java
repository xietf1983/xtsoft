package com.xtsoft.kernel.counter.model;

import java.io.Serializable;

/******
 * 
 * @author x61
 * 
 */
public class Counter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2295620525905383029L;
	public Counter() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCurrentId() {
		return currentId;
	}

	public void setCurrentId(long currentId) {
		this.currentId = currentId;
	}

	private String name;
	private long currentId;

}