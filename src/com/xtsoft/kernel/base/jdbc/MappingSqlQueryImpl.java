package com.xtsoft.kernel.base.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;

public class MappingSqlQueryImpl<T> extends org.springframework.jdbc.object.MappingSqlQuery<T> implements MappingSqlQuery<T> {

	public MappingSqlQueryImpl(DataSource dataSource, String sql, int[] types, RowMapper<T> rowMapper) {

		super(dataSource, sql);

		for (int type : types) {
			declareParameter(new SqlParameter(type));
		}

		_rowMapper = rowMapper;

		compile();
	}

	protected T mapRow(ResultSet rs, int rowNumber) throws SQLException {
		return _rowMapper.mapRow(rs, rowNumber);
	}

	private RowMapper<T> _rowMapper;

}