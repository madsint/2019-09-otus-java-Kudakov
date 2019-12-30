package ru.otus.l111.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ru.otus.l111.dao.base.BasicDAOImpl;
import ru.otus.l111.dataset.UserDataSet;

public class UserDAOImpl extends BasicDAOImpl<UserDataSet> implements UserDAO {

	private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
	private static final String SELECT_USER_BY_NAME = "SELECT * FROM users WHERE name = ?";
	private static final String SELECT_ALL_USERS = "SELECT * FROM users";
	private static final String UPDATE_USER = "UPDATE users SET name = ?, age = ? WHERE id = ?";
	private static final String INSERT_USER = "INSERT INTO users (name, age) VALUES (?, ?)";
	private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";

	public UserDAOImpl(final Connection connection) {
		super(connection, UserDataSet.class);
	}

	@Override
	public void save(UserDataSet dataSet) throws SQLException {
		if (!exists(dataSet)) {
			update(INSERT_USER, dataSet, "name", "age");
		} else {
			update(UPDATE_USER, dataSet, "name", "age", "id");
		}
	}

	@Override
	public UserDataSet load(Long id) throws SQLException {
		return selectSingle(SELECT_USER_BY_ID, id);
	}

	@Override
	public UserDataSet loadByName(String name) throws SQLException {
		return selectSingle(SELECT_USER_BY_NAME, name);
	}

	@Override
	public List<UserDataSet> loadAll() throws SQLException {
		return select(SELECT_ALL_USERS);
	}

	@Override
	public void delete(UserDataSet dataSet) throws SQLException {
		update(DELETE_USER, dataSet, "id");
	}

}
