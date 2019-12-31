package ru.otus.l121.dataset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
public class AddressDataSet extends DataSet {

	private String street;
	private UserDataSet user;

	public AddressDataSet() {
		super();
	}

	public AddressDataSet(Long id, String street) {
		this.setId(id);
		this.setStreet(street);
	}

	public AddressDataSet(String street) {
		this(null, street);
	}

	@Column(name = "street")
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public UserDataSet getUser() {
		return user;
	}

	public void setUser(UserDataSet user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Address ID = " + getId() + ", street = " + getStreet();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof AddressDataSet)) {
			return false;
		}
		AddressDataSet other = (AddressDataSet) obj;
		return getId() != null && getId().equals(other.getId());
	}

	@Override
	public int hashCode() {
		return getId().intValue();
	}
}
