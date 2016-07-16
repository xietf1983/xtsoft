package com.xtsoft.kernel.counter.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xtsoft.kernel.counter.model.Counter;
import com.xtsoft.kernel.counter.model.CounterRegister;


import javax.sql.DataSource;

/*******************************************************************************
 * 
 * @author x61
 * 
 */
public class CounterPersistence {
	public long increment() throws SQLException {
		return increment(_NAME);
	}

	public long increment(String name) throws SQLException {
		return increment(name, _MINIMUM_INCREMENT_SIZE);
	}

	public long increment(String name, int size) throws SQLException {
		if (size < _MINIMUM_INCREMENT_SIZE) {
			size = _MINIMUM_INCREMENT_SIZE;
		}
		CounterRegister register = getCounterRegister(name);
		synchronized (register) {
			long newValue = register.getCurrentValue() + size;
			if (newValue > register.getRangeMax()) {
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				try {
					connection = getConnection();
					preparedStatement = connection.prepareStatement(_SQL_UPDATE_NAME_BY_NAME);
					preparedStatement.setLong(1, newValue + register.getRangeSize());
					preparedStatement.setString(2, name);
					preparedStatement.executeUpdate();
					long rangeMax = register.getCurrentValue() + register.getRangeSize();
					register.setRangeMax(rangeMax);
					register.setCurrentValue(newValue);
					if(!connection.getAutoCommit()){
						connection.commit();
					}
				} finally {
					cleanUp(connection, preparedStatement);
				}
			} else {
				register.setCurrentValue(newValue);
			}
			return newValue;
		}
	}

	protected CounterRegister getCounterRegister(String name) throws SQLException {
		CounterRegister register = _registerLookup.get(name);
		if (register == null) {
			register = createCounterRegister(name);
			_registerLookup.put(name, register);
		}
		return register;
	}

	protected CounterRegister createCounterRegister(String name) throws SQLException {

		return createCounterRegister(name, _COUNTER_INCREMENT);
	}

	protected CounterRegister createCounterRegister(String name, long size) throws SQLException {
		long rangeMin = 0;
		long rangeMax = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(_SQL_SELECT_ID_BY_NAME);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				rangeMin = _DEFAULT_CURRENT_ID;

				if (size > rangeMin) {
					rangeMin = size;
				}
				rangeMax = rangeMin + size;
				preparedStatement = connection.prepareStatement(_SQL_INSERT);
				preparedStatement.setString(1, name);
				preparedStatement.setLong(2, rangeMax);
				preparedStatement.executeUpdate();
				if(!connection.getAutoCommit()){
					connection.commit();
				}
			} else {
				rangeMin = resultSet.getLong("currentId");
				if (size > rangeMin) {
					rangeMin = size;
				}
				rangeMax = rangeMin + size;
				preparedStatement = connection.prepareStatement(_SQL_UPDATE_NAME_BY_NAME);
				preparedStatement.setLong(1, rangeMax);
				preparedStatement.setString(2, name);
				preparedStatement.executeUpdate();
				if(!connection.getAutoCommit()){
					connection.commit();
				}
			}
		} finally {
			cleanUp(connection, preparedStatement, resultSet);
		}
		CounterRegister register = new CounterRegister(name, rangeMin, rangeMax, _COUNTER_INCREMENT);
		return register;
	}

	protected Connection getConnection() throws SQLException {
		Connection connection = getDataSource().getConnection();

		return connection;
	}

	public static void cleanUp(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException sqle) {
			if (_log.isWarnEnabled()) {
				_log.warn(sqle.getMessage());
			}
		}
	}

	public static void cleanUp(Connection connection, Statement statement) {
		cleanUp(statement);
		cleanUp(connection);
	}

	public static void cleanUp(Connection connection, Statement statement, ResultSet resultSet) {

		cleanUp(resultSet);
		cleanUp(statement);
		cleanUp(connection);
	}

	public static void cleanUp(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException sqle) {
			if (_log.isWarnEnabled()) {
				_log.warn(sqle.getMessage());
			}
		}
	}

	public static void cleanUp(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException sqle) {
			if (_log.isWarnEnabled()) {
				_log.warn(sqle.getMessage());
			}
		}
	}

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final int _DEFAULT_CURRENT_ID = 10000;
	private static final int _MINIMUM_INCREMENT_SIZE = 1;
	private static final int _COUNTER_INCREMENT = 500;
	private Map<String, CounterRegister> _registerLookup = new ConcurrentHashMap<String, CounterRegister>();
	private static final String _NAME = Counter.class.getName();
	private static final String _SQL_SELECT_ID_BY_NAME = "select currentId from Counter where name = ?";
	private static final String _SQL_INSERT = "insert into Counter(name, currentId) values (?, ?)";

	private static final String _SQL_UPDATE_NAME_BY_NAME = "update Counter set currentId = ? where name = ?";
	private static Log _log = LogFactory.getLog(CounterPersistence.class);

}
