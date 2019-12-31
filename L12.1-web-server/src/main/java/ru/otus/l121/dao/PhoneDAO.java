package ru.otus.l121.dao;

import java.sql.SQLException;
import java.util.List;

import ru.otus.l121.dao.base.BasicDAO;
import ru.otus.l121.dataset.PhoneDataSet;

public interface PhoneDAO extends BasicDAO<PhoneDataSet> {

	void save(PhoneDataSet dataSet) throws SQLException;

	PhoneDataSet load(Long id) throws SQLException;

	PhoneDataSet loadByNumber(String number) throws SQLException;

	List<PhoneDataSet> loadByUserId(Long userId) throws SQLException;

	List<PhoneDataSet> loadAll() throws SQLException;

	void delete(PhoneDataSet dataSet) throws SQLException;

}
