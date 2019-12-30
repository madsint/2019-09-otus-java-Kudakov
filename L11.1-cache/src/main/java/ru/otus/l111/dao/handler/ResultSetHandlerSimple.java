package ru.otus.l111.dao.handler;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetHandlerSimple<T> implements ResultSetHandler<T> {

	Class<? extends T> type;

	public ResultSetHandlerSimple(Class<? extends T> type) {
		this.type = type;
	}

	public List<T> handle(final ResultSet rs) throws SQLException {
		List<T> result = new ArrayList<>();
		while (rs.next()) {
			T instance = newInstance(type);
			result.add(populateObject(rs, instance));
		}
		return result;
	}

	private T newInstance(Class<? extends T> type) throws SQLException {
		try {
			return type.newInstance();
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

	private T populateObject(final ResultSet rs, T instance) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();
		try {
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				ObjectHelper.setValueByName(instance, metaData.getColumnName(i), rs.getObject(i));
			}
		} catch (Exception e) {
			throw new SQLException(e);
		}
		return instance;
	}

}
