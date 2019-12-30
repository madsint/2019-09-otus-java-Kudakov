package ru.otus.l111;

import java.sql.SQLException;

import ru.otus.l111.cache.CacheConfiguration;
import ru.otus.l111.cache.CacheFactory;
import ru.otus.l111.dataset.*;
import ru.otus.l111.dbservice.*;

public class Main {

	public static void main(String[] args) throws InterruptedException, SQLException {

		DBService service = new DBServiceHibernateImpl(CacheFactory.getCache(new CacheConfiguration()));

		System.out.println("Creating a user:");
		UserDataSet user = new UserDataSet("Johnny Johnny", 35);
		user.setAddress(new AddressDataSet("My Green Street"));
		user.addPhone(new PhoneDataSet("0147258369"));
		user.addPhone(new PhoneDataSet("0123456789"));
		service.save(user);
		System.out.println("User created: " + user);

		System.out.println("Loading the user first time, user not in cache, execute SELECT:");
		user = service.load(user.getId());
		System.out.println("User loaded: " + user);

		System.out.println("Loading the user second time, user in cache, no SELECT:");
		user = service.load(user.getId());
		System.out.println("User loaded: " + user);

		Thread.sleep(1500);

		System.out.println("Loading the user after delay - should be evicted from cache, execute SELECT:");
		user = service.load(user.getId());
		System.out.println("User loaded: " + user);

		service.shutdown();
	}

}
