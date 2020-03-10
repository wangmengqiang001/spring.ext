package com.crossbridge.kernel.dba.base;

import java.util.Collection;

public interface IBaseDao<T> {
	boolean save(Class<T> t);
	boolean remove(Class<T> t);
	Collection<Class<T>> find(String query);
	boolean update(Class<T> t);
}
