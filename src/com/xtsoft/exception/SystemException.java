package com.xtsoft.exception;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: nanwang
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */
public class SystemException extends Exception {

	private String name = "com.lytx.webservice.exception.SystemException";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SystemException() {
		super();
	}

	public SystemException(String msg) {
		super(msg);
	}

	public SystemException(Throwable e) {
		super(e);
	}

	public SystemException(String msg, Throwable e) {
		super(msg, e);
	}
}
