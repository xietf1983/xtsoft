package com.xtsoft.kernel.base.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {

	public static final RowMapper<Integer> COUNT = new CountRowMapper();

	public T mapRow(ResultSet rs, int rowNumber) throws SQLException;

}