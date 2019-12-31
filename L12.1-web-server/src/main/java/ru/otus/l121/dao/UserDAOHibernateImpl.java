package ru.otus.l121.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ru.otus.l121.dataset.UserDataSet;

public class UserDAOHibernateImpl implements UserDAO {

	private final Session session;

	public UserDAOHibernateImpl(final Session session) {
		this.session = session;
	}

	@Override
	public void save(UserDataSet dataSet) {
		session.saveOrUpdate(dataSet);
	}

	@Override
	public UserDataSet load(Long id) {
		return session.load(UserDataSet.class, id);
	}

	@Override
	public UserDataSet loadByName(String name) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<UserDataSet> criteria = builder.createQuery(UserDataSet.class);
		Root<UserDataSet> from = criteria.from(UserDataSet.class);
		criteria.where(builder.equal(from.get("name"), name));
		Query<UserDataSet> query = session.createQuery(criteria);
		return query.uniqueResult();
	}

	@Override
	public List<UserDataSet> loadAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<UserDataSet> criteria = builder.createQuery(UserDataSet.class);
		criteria.from(UserDataSet.class);
		return session.createQuery(criteria).list();
	}

	@Override
	public void delete(UserDataSet dataSet) throws SQLException {
		session.delete(dataSet);
	}

}
