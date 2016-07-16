package com.xtsoft.util;

import com.xtsoft.exception.SystemException;

public interface PropertiesConfig {
	public void fromXmlStr(String xml) throws SystemException;

	public void saveProp() throws SystemException;

	public String toXmlStr(boolean isRuntime) throws SystemException;

	public void reloadProps() throws SystemException;

	public void setPropRunTime(String name, String value) throws SystemException;
}
