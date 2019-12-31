package ru.otus.l121.dao;

import java.sql.SQLException;
import java.util.List;

import ru.otus.l121.dao.base.BasicDAO;
import ru.otus.l121.dataset.AddressDataSet;

public interface AddressDAO extends BasicDAO<AddressDataSet> {

	void save(AddressDataSet dataSet) throws SQLException;

	AddressDataSet load(Long id) throws SQLException;

	AddressDataSet loadByStreet(String street) throws SQLException;

	AddressDataSet loadByUserId(Long userId) throws SQLException;

	List<AddressDataSet> loadAll() throws SQLException;

	void delete(AddressDataSet dataSet) throws SQLException;
}
