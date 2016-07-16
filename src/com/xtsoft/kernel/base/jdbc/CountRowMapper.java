package com.xtsoft.kernel.base.jdbc;

import java.sql.ResultSet;

public class CountRowMapper implements RowMapper<Integer> {

	public Integer mapRow(ResultSet rs, int rowNumber) {
		return 1;
	}

}