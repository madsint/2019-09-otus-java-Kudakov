package ru.otus.l111.dao.base;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ru.otus.l111.dao.executor.QueryExecutor;
import ru.otus.l111.dao.handler.ResultSetHandler;
import ru.otus.l111.dao.handler.ResultSetHandlerSimple;
import ru.otus.l111.dataset.DataSet;

public abstract class BasicDAOImpl<T extends DataSet> implements BasicDAO<T> {

	private final Connection connection;
	private final Class<T> type;

	public BasicDAOImpl(Connection connection, Class<T> type) {
		this.connection = connection;
		this.type = type;
	}

	protected T selectSingle(String sql, Object... params) throws SQLException {
		List<T> result = select(sql, params);
		return result.size() > 0 ? result.get(0) : null;
	}

	protected List<T> select(String sql, Object... params) throws SQLException {
		QueryExecutor executor = new QueryExecutor(connection);
		ResultSetHandler<T> handler = new ResultSetHandlerSimple<>(type);
		return executor.executeQuery(sql, handler, params);
	}

	protected int update(String sql, T dataSet, String... names) throws SQLException {
		if (dataSet == null) {
			throw new SQLException(type.getName() + " not found: " + dataSet);
		}
		QueryExecutor executor = new QueryExecutor(connection);
		return executor.executeUpdate(sql, dataSet, names);
	}

	protected boolean exists(T dataSet) throws SQLException {
		if (dataSet == null) {
			throw new SQLException(type.getName() + " not found: " + dataSet);
		}
		return load(dataSet.getId()) != null;
	}

	@Override
	public abstract void save(T dataSet) throws SQLException;

	@Override
	public abstract T load(Long id) throws SQLException;

	@Override
	public abstract List<T> loadAll() throws SQLException;

	@Override
	public abstract void delete(T dataSet) throws SQLException;
}
