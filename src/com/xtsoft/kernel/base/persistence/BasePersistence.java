package com.xtsoft.kernel.base.persistence;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import com.xtsoft.kernel.base.entity.BasePersistenceEntity;
import com.xtsoft.kernel.base.persistence.listener.ModelListener;

public class BasePersistence<T extends BasePersistenceEntity<T>> extends SqlSessionDaoSupport {
	public static final String COUNT_COLUMN_NAME = "COUNT_VALUE";
	protected ModelListener<T>[] listeners = new ModelListener[0];
  
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ModelListener<T>[] getListeners() {
		return listeners;
	}

	public void insertEntity(T entity) throws Exception {
		insertEntity(getInsertStatement(entity.getClass()), entity);
	}

	public void registerListener(ModelListener<T> listener) {
		// List<ModelListener<T>> listenersList = ListUtil.fromArray(listeners);

		// listenersList.add(listener);

		// listeners = listenersList.toArray(new
		// ModelListener[listenersList.size()]);
	}
	public void initialize() {
		
	}

	public void insertEntityList(List<T> list) throws Exception {
		if (list != null) {
			for (T entity : list) {
				insertEntity(entity);
			}
		}
	}
	public void insertEntity(String statement, T model) throws Exception {
		for (ModelListener<T> listener : listeners) {
			listener.onBeforeCreate(model);
		}
		getSqlSession().insert(statement, model);
		for (ModelListener<T> listener : listeners) {
			listener.onAfterCreate(model);
		}
	}

	public void updateEntity(T model) throws Exception {
		updateEntity(getUpdateStatement(model.getClass()), model);
	}

	public void updateEntityList(List<T> list) throws Exception {
		if (list != null) {
			for (T entity : list) {
				updateEntity(entity);
			}
		}
	}

	public void updateEntity(String statement, T model) throws Exception {
		for (ModelListener<T> listener : listeners) {
			listener.onBeforeUpdate(model);
		}
		getSqlSession().update(statement, model);
		for (ModelListener<T> listener : listeners) {
			listener.onAfterUpdate(model);
		}
	}

	public void removeEntity(T model) throws Exception {
		removeEntity(getDeleteStatement(model.getClass()), model);
	}

	public void removeEntity(String statement, T model) throws Exception {
		for (ModelListener<T> listener : listeners) {
			listener.onBeforeRemove(model);
		}
		getSqlSession().delete(statement, model);
		for (ModelListener<T> listener : listeners) {
			listener.onAfterRemove(model);
		}
	}

	public Object findByPrimaryKey(T model) {
		return getSqlSession().selectOne(getSelectStatement(model.getClass()), model);
	}

	public List selectList(String statement) {
		return selectList(statement, null);
	}

	public List selectList(String statement, QueryParameter parameter) {
		return getSqlSession().selectList(statement, parameter);
	}

	public Object selectOne(String statement, QueryParameter parameter) {
		return getSqlSession().selectOne(statement, parameter);
	}

	public String getInsertStatement(Class<?> persistentObjectClass) {
		return getStatement(persistentObjectClass, "insert");
	}

	public String getUpdateStatement(Class<?> persistentObjectClass) {
		return getStatement(persistentObjectClass, "update");
	}

	public String getDeleteStatement(Class<?> persistentObjectClass) {
		return getStatement(persistentObjectClass, "delete");
	}

	public String getSelectStatement(Class<?> persistentObjectClass) {
		return getStatement(persistentObjectClass, "select");
	}

	private String getStatement(Class<?> persistentObjectClass, String prefix) {
		String statement = null;
		statement = prefix + persistentObjectClass.getSimpleName();
		return statement;
	}

	public void close() {
		getSqlSession().close();
	}

	public void commit() {
		getSqlSession().commit();
	}

	public void rollback() {
		getSqlSession().rollback();
	}
	protected ClassLoader getClassLoader() {
		Class<?> clazz = getClass();

		return clazz.getClassLoader();
	}

	private static Log _log = LogFactory.getLog(BasePersistence.class);
}
