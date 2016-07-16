package com.xtsoft.kernel.base.persistence.listener;

import com.xtsoft.kernel.base.entity.BasePersistenceEntity;

public class BaseModelListener<T extends BasePersistenceEntity<T>> implements ModelListener<T> {
	@SuppressWarnings("unused")
	public void onAfterAddAssociation(Object classPK, String associationClassName, Object associationClassPK) throws ModelListenerException {
	}

	@SuppressWarnings("unused")
	public void onAfterCreate(T model) throws ModelListenerException {
	}

	@SuppressWarnings("unused")
	public void onAfterRemove(T model) throws ModelListenerException {
	}

	@SuppressWarnings("unused")
	public void onAfterRemoveAssociation(Object classPK, String associationClassName, Object associationClassPK) throws ModelListenerException {
	}

	@SuppressWarnings("unused")
	public void onAfterUpdate(T model) throws ModelListenerException {
	}

	@SuppressWarnings("unused")
	public void onBeforeAddAssociation(Object classPK, String associationClassName, Object associationClassPK) throws ModelListenerException {
	}

	@SuppressWarnings("unused")
	public void onBeforeCreate(T model) throws ModelListenerException {
	}

	@SuppressWarnings("unused")
	public void onBeforeRemove(T model) throws ModelListenerException {
	}

	@SuppressWarnings("unused")
	public void onBeforeRemoveAssociation(Object classPK, String associationClassName, Object associationClassPK) throws ModelListenerException {
	}

	@SuppressWarnings("unused")
	public void onBeforeUpdate(T model) throws ModelListenerException {
	}

}
