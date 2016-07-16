package com.xtsoft.kernel.base.persistence.listener;


public interface ModelListener<T> {
	public void onAfterAddAssociation(Object classPK, String associationClassName, Object associationClassPK) throws ModelListenerException;

	public void onAfterCreate(T model) throws ModelListenerException;

	public void onAfterRemove(T model) throws ModelListenerException;

	public void onAfterRemoveAssociation(Object classPK, String associationClassName, Object associationClassPK) throws ModelListenerException;

	public void onAfterUpdate(T model) throws ModelListenerException;

	public void onBeforeAddAssociation(Object classPK, String associationClassName, Object associationClassPK) throws ModelListenerException;

	public void onBeforeCreate(T model) throws ModelListenerException;

	public void onBeforeRemove(T model) throws ModelListenerException;

	public void onBeforeRemoveAssociation(Object classPK, String associationClassName, Object associationClassPK) throws ModelListenerException;

	public void onBeforeUpdate(T model) throws ModelListenerException;

}
