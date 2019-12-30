package ru.otus.l111.dataset;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserDataSet extends DataSet {

	private String name;
	private int age;
	private AddressDataSet address;
	private List<PhoneDataSet> phones = new ArrayList<>();

	public UserDataSet() {
		super();
	}

	public UserDataSet(Long id, String name, int age) {
		this.setId(id);
		this.setName(name);
		this.setAge(age);
	}

	public UserDataSet(String name, int age) {
		this(null, name, age);
	}

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	public AddressDataSet getAddress() {
		return address;
	}

	public void setAddress(AddressDataSet address) {
		this.address = address;
		address.setUser(this);
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<PhoneDataSet> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneDataSet> phones) {
		this.phones = phones;
		for (PhoneDataSet phone : phones) {
			phone.setUser(this);
		}
	}

	public void addPhone(PhoneDataSet phone) {
		phones.add(phone);
		phone.setUser(this);
	}

	public void removePhone(PhoneDataSet phone) {
		phones.remove(phone);
		phone.setUser(null);
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "age")
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User ID = " + getId() + ", name = " + getName() + ", age = " + getAge() + ", address = " + getAddress()
				+ ", phones = " + getPhones();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof UserDataSet)) {
			return false;
		}
		UserDataSet other = (UserDataSet) obj;
		return getId() != null && getId().equals(other.getId());
	}

	@Override
	public int hashCode() {
		return getId().intValue();
	}
}
