package ru.otus.l121.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ru.otus.l121.dataset.AddressDataSet;

public class AddressDAOHibernateImpl implements AddressDAO {

	private final Session session;

	public AddressDAOHibernateImpl(final Session session) {
		this.session = session;
	}

	@Override
	public void save(AddressDataSet dataSet) throws SQLException {
		session.saveOrUpdate(dataSet);
	}

	@Override
	public AddressDataSet load(Long id) throws SQLException {
		return session.load(AddressDataSet.class, id);
	}

	@Override
	public AddressDataSet loadByStreet(String street) throws SQLException {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<AddressDataSet> criteria = builder.createQuery(AddressDataSet.class);
		Root<AddressDataSet> from = criteria.from(AddressDataSet.class);
		criteria.where(builder.equal(from.get("street"), street));
		Query<AddressDataSet> query = session.createQuery(criteria);
		return query.uniqueResult();
	}

	@Override
	public AddressDataSet loadByUserId(Long userId) throws SQLException {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<AddressDataSet> criteria = builder.createQuery(AddressDataSet.class);
		Root<AddressDataSet> from = criteria.from(AddressDataSet.class);
		criteria.where(builder.equal(from.get("user"), userId));
		Query<AddressDataSet> query = session.createQuery(criteria);
		return query.uniqueResult();
	}

	@Override
	public List<AddressDataSet> loadAll() throws SQLException {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<AddressDataSet> criteria = builder.createQuery(AddressDataSet.class);
		criteria.from(AddressDataSet.class);
		return session.createQuery(criteria).list();
	}

	@Override
	public void delete(AddressDataSet dataSet) throws SQLException {
		session.delete(dataSet);
	}

}
