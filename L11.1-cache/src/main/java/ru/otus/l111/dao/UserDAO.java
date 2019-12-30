package ru.otus.l111.dao;

import java.sql.SQLException;
import java.util.List;

import ru.otus.l111.dao.base.BasicDAO;
import ru.otus.l111.dataset.UserDataSet;

public interface UserDAO extends BasicDAO<UserDataSet> {

	void save(UserDataSet dataSet) throws SQLException;

	UserDataSet load(Long id) throws SQLException;

	UserDataSet loadByName(String name) throws SQLException;

	List<UserDataSet> loadAll() throws SQLException;

	void delete(UserDataSet dataSet) throws SQLException;
}
