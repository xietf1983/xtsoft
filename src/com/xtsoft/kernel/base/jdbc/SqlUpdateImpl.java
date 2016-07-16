package com.xtsoft.kernel.base.jdbc;

import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlParameter;

public class SqlUpdateImpl
	extends org.springframework.jdbc.object.SqlUpdate implements SqlUpdate {

	public SqlUpdateImpl(DataSource dataSource, String sql, int[] types) {
		super(dataSource, sql);

		for (int type : types) {
			declareParameter(new SqlParameter(type));
		}

		compile();
	}

}