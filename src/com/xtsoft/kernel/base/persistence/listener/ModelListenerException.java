package com.xtsoft.kernel.base.persistence.listener;

public class ModelListenerException extends Exception {
	public ModelListenerException() {
		super();
	}

	public ModelListenerException(String msg) {
		super(msg);
	}

	public ModelListenerException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ModelListenerException(Throwable cause) {
		super(cause);
	}

}
