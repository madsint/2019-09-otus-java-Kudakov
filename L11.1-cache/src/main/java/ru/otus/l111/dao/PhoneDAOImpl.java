package ru.otus.l111.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ru.otus.l111.dao.base.BasicDAOImpl;
import ru.otus.l111.dataset.PhoneDataSet;

public class PhoneDAOImpl extends BasicDAOImpl<PhoneDataSet> implements PhoneDAO {

	private static final String SELECT_PHONE_BY_ID = "SELECT * FROM phones WHERE id = ?";
	private static final String SELECT_PHONE_BY_USER_ID = "SELECT * FROM phones WHERE user_id = ?";
	private static final String SELECT_PHONE_BY_NUMBER = "SELECT * FROM phones WHERE number = ?";
	private static final String SELECT_ALL_PHONES = "SELECT * FROM phones";
	private static final String UPDATE_PHONE = "UPDATE phones SET number = ?, user_id = ? WHERE id = ?";
	private static final String INSERT_PHONE = "INSERT INTO phones (number, user_id) VALUES (?, ?)";
	private static final String DELETE_PHONE = "DELETE FROM phones WHERE id = ?";

	public PhoneDAOImpl(final Connection connection) {
		super(connection, PhoneDataSet.class);
	}

	@Override
	public void save(PhoneDataSet dataSet) throws SQLException {
		if (!exists(dataSet)) {
			update(INSERT_PHONE, dataSet, "number", "user_id");
		} else {
			update(UPDATE_PHONE, dataSet, "number", "user_id", "id");
		}
	}

	@Override
	public PhoneDataSet load(Long id) throws SQLException {
		return selectSingle(SELECT_PHONE_BY_ID, id);
	}

	@Override
	public PhoneDataSet loadByNumber(String number) throws SQLException {
		return selectSingle(SELECT_PHONE_BY_NUMBER, number);
	}

	@Override
	public List<PhoneDataSet> loadByUserId(Long userId) throws SQLException {
		return select(SELECT_PHONE_BY_USER_ID, userId);
	}

	@Override
	public List<PhoneDataSet> loadAll() throws SQLException {
		return select(SELECT_ALL_PHONES);
	}

	@Override
	public void delete(PhoneDataSet dataSet) throws SQLException {
		update(DELETE_PHONE, dataSet, "id");
	}

}
