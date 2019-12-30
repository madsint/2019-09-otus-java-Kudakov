package ru.otus.l111.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ru.otus.l111.dao.base.BasicDAOImpl;
import ru.otus.l111.dataset.AddressDataSet;

public class AddressDAOImpl extends BasicDAOImpl<AddressDataSet> implements AddressDAO {

	private static final String SELECT_ADDRESS_BY_ID = "SELECT * FROM addresses WHERE id = ?";
	private static final String SELECT_ADDRESS_BY_USER_ID = "SELECT * FROM addresses WHERE user_id = ?";
	private static final String SELECT_ADDRESS_BY_STREET = "SELECT * FROM addresses WHERE street = ?";
	private static final String SELECT_ALL_ADDRESSES = "SELECT * FROM addresses";
	private static final String UPDATE_ADDRESS = "UPDATE addresses SET street = ? user_id = ? WHERE id = ?";
	private static final String INSERT_ADDRESS = "INSERT INTO addresses (street, user_id) VALUES (?, ?)";
	private static final String DELETE_ADDRESS = "DELETE FROM addresses WHERE id = ?";

	public AddressDAOImpl(final Connection connection) {
		super(connection, AddressDataSet.class);
	}

	@Override
	public void save(AddressDataSet dataSet) throws SQLException {
		if (!exists(dataSet)) {
			update(INSERT_ADDRESS, dataSet, "street", "user_id");
		} else {
			update(UPDATE_ADDRESS, dataSet, "street", "user_id", "id");
		}
	}

	@Override
	public AddressDataSet load(Long id) throws SQLException {
		return selectSingle(SELECT_ADDRESS_BY_ID, id);
	}

	@Override
	public AddressDataSet loadByStreet(String street) throws SQLException {
		return selectSingle(SELECT_ADDRESS_BY_STREET, street);
	}

	@Override
	public AddressDataSet loadByUserId(Long userId) throws SQLException {
		return selectSingle(SELECT_ADDRESS_BY_USER_ID, userId);
	}

	@Override
	public List<AddressDataSet> loadAll() throws SQLException {
		return select(SELECT_ALL_ADDRESSES);
	}

	@Override
	public void delete(AddressDataSet dataSet) throws SQLException {
		update(DELETE_ADDRESS, dataSet, "id");
	}

}
