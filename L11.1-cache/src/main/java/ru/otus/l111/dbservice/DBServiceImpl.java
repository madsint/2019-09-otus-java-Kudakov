package ru.otus.l111.dbservice;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.otus.l111.connection.ConnectionHelper;
import ru.otus.l111.dao.*;
import ru.otus.l111.dataset.*;

public class DBServiceImpl implements DBService {

	private Connection connection;

	public DBServiceImpl() throws SQLException {
		this.connection = ConnectionHelper.getConnection();
	}

	@Override
	public void save(UserDataSet user) throws SQLException {
		try {
			connection.setAutoCommit(false);
			// Save user
			UserDAO userDao = new UserDAOImpl(connection);
			userDao.save(user);
			// Save address
			AddressDataSet address = user.getAddress();
			if (address != null) {
				AddressDAO addressDao = new AddressDAOImpl(connection);
				addressDao.save(address);
			}
			// Save phones
			PhoneDAO phoneDao = new PhoneDAOImpl(connection);
			for (PhoneDataSet phone : user.getPhones()) {
				phoneDao.save(phone);
			}
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
		} finally {
			connection.setAutoCommit(true);
		}
	}

	@Override
	public void delete(UserDataSet user) throws SQLException {
		try {
			connection.setAutoCommit(false);
			// Delete address
			AddressDataSet address = user.getAddress();
			if (address != null) {
				AddressDAO addressDao = new AddressDAOImpl(connection);
				addressDao.delete(address);
			}
			// Delete phones
			PhoneDAO phoneDao = new PhoneDAOImpl(connection);
			for (PhoneDataSet phone : user.getPhones()) {
				phoneDao.delete(phone);
			}
			// Delete user
			UserDAO userDao = new UserDAOImpl(connection);
			userDao.delete(user);
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
		} finally {
			connection.setAutoCommit(true);
		}
	}

	@Override
	public UserDataSet load(Long id) throws SQLException {
		UserDataSet user = null;
		try {
			connection.setAutoCommit(false);
			UserDAO userDao = new UserDAOImpl(connection);
			user = userDao.load(id);
			user.setAddress(loadAddressByUserId(user.getId()));
			user.setPhones(loadPhonesByUserId(user.getId()));
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
		} finally {
			connection.setAutoCommit(true);
		}
		return user;
	}

	@Override
	public UserDataSet loadByName(String name) throws SQLException {
		UserDataSet user = null;
		try {
			connection.setAutoCommit(false);
			UserDAO userDao = new UserDAOImpl(connection);
			user = userDao.loadByName(name);
			user.setAddress(loadAddressByUserId(user.getId()));
			user.setPhones(loadPhonesByUserId(user.getId()));
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
		} finally {
			connection.setAutoCommit(true);
		}
		return user;
	}

	@Override
	public List<UserDataSet> loadAll() throws SQLException {
		List<UserDataSet> users = new ArrayList<>();
		try {
			connection.setAutoCommit(false);
			UserDAO userDao = new UserDAOImpl(connection);
			users = userDao.loadAll();
			for (UserDataSet user : users) {
				user.setAddress(loadAddressByUserId(user.getId()));
				user.setPhones(loadPhonesByUserId(user.getId()));
			}
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
		} finally {
			connection.setAutoCommit(true);
		}
		return users;
	}

	@Override
	public void shutdown() throws SQLException {
		connection.close();
	}

	@Override
	public AddressDataSet loadAddressByUserId(Long userId) throws SQLException {
		AddressDAO addressDao = new AddressDAOImpl(connection);
		return addressDao.loadByUserId(userId);
	}

	@Override
	public List<PhoneDataSet> loadPhonesByUserId(Long userId) throws SQLException {
		PhoneDAO phoneDao = new PhoneDAOImpl(connection);
		return phoneDao.loadByUserId(userId);
	}

	@Override
	public AddressDataSet loadAddressById(Long id) throws SQLException {
		AddressDAO addressDao = new AddressDAOImpl(connection);
		return addressDao.load(id);
	}

	@Override
	public PhoneDataSet loadPhoneById(Long id) throws SQLException {
		PhoneDAO phoneDao = new PhoneDAOImpl(connection);
		return phoneDao.load(id);
	}

}
