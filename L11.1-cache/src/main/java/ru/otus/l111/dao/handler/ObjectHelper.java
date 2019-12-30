package ru.otus.l111.dao.handler;

import java.lang.reflect.Field;
import java.sql.SQLException;

public class ObjectHelper {

	public static <T> Object getValueByName(T object, String name) throws SQLException {
		Object value = null;
		try {
			Field field = null;
			String[] names = name.split("_");
			try {
				field = object.getClass().getDeclaredField(names[0]);
			} catch (Exception e) {
				field = object.getClass().getSuperclass().getDeclaredField(names[0]);
			}
			field.setAccessible(true);
			if (names.length == 1) {
				value = field.get(object);
			} else {
				value = ObjectHelper.getValueByName(field.get(object), names[1]);
			}
		} catch (Exception e) {
			throw new SQLException(e);
		}
		return value;
	}

	public static <T> void setValueByName(T object, String name, Object value) throws SQLException {
		try {
			Field field = null;
			String[] names = name.split("_");
			try {
				field = object.getClass().getDeclaredField(names[0]);
			} catch (Exception e) {
				field = object.getClass().getSuperclass().getDeclaredField(names[0]);
			}
			field.setAccessible(true);
			if (names.length == 1) {
				field.set(object, value);
			} else {
				Object subObject = field.getType().newInstance();
				ObjectHelper.setValueByName(subObject, names[1], value);
				field.set(object, subObject);
			}
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}
}