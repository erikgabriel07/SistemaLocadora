package com.locadora.project.util;

import java.lang.reflect.Field;

public class ObjectFactory<T> {
	private final T prototype;
	
	public ObjectFactory(T prototype) {
		this.prototype = prototype;
	}
	
	@SuppressWarnings("unchecked")
	public T createInstance() {
		try {
			T newInstance = (T)prototype.getClass().getDeclaredConstructor().newInstance();
			
			for (Field field : prototype.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object value = field.get(prototype);
				field.set(newInstance, value);
			}
			return newInstance;
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível criar uma instância do objeto: " + e.getMessage(), e);
		}
	}
}