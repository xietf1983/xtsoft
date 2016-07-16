package com.xtsoft.kernel.counter;

import java.sql.SQLException;

import com.xtsoft.kernel.counter.persistence.CounterPersistence;

public class CounterUtil {
	private static CounterPersistence _persistence;

	public static CounterPersistence getPersistent() {
		if (_persistence == null) {
			throw new RuntimeException("CounterPersistence is not set");
		}

		return _persistence;
	}

	public void setPersistence(CounterPersistence persistence) {
		_persistence = persistence;
	}

	public static long increment() throws SQLException {
		return getPersistent().increment();
	}

	public static long increment(String name) throws SQLException {
		return getPersistent().increment(name);
	}

	public static long increment(String name, int size) throws SQLException {
		return getPersistent().increment(name, size);
	}

}
