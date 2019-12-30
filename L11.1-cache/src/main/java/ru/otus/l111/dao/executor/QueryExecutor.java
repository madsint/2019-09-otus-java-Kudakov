package ru.otus.l111.dao.executor;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ru.otus.l111.dao.handler.*;
import ru.otus.l111.dataset.DataSet;

public class QueryExecutor {

	private Connection connection;

	public QueryExecutor(Connection connection) {
		this.connection = connection;
	}

	public <T extends DataSet> List<T> executeQuery(String sql, ResultSetHandler<T> handler, Object... params)
			throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			prepare(stmt, params);
			ResultSet rs = stmt.executeQuery();
			return handler.handle(rs);
		}
	}

	public <T extends DataSet> int executeUpdate(String sql, T dataSet, String... names) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			prepare(stmt, getParams(dataSet, names));
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected == 0) {
				throw new SQLException("No rows have been affected");
			}
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					dataSet.setId(generatedKeys.getLong(1));
				}
			}
			return rowsAffected;
		}
	}

	private void prepare(PreparedStatement stmt, Object... params) throws SQLException {
		ParameterMetaData metadata = stmt.getParameterMetaData();
		int count = metadata.getParameterCount();
		if (count != params.length) {
			throw new SQLException("Wrong number of parameters");
		}
		for (int i = 0; i < count; i++) {
			stmt.setObject(i + 1, params[i]);
		}
	}

	private Object[] getParams(Object obj, String[] names) throws SQLException {
		Object[] params = new Object[names.length];
		try {
			for (int i = 0; i < names.length; i++) {
				params[i] = ObjectHelper.getValueByName(obj, names[i]);
			}
		} catch (Exception e) {
			throw new SQLException(e);
		}
		return params;
	}

}
