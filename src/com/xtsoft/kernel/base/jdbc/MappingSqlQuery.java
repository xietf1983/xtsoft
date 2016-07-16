package com.xtsoft.kernel.base.jdbc;

import java.util.List;

public interface MappingSqlQuery<T> {

	public List<T> execute(Object... params);

}