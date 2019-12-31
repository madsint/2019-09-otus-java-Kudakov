package ru.otus.l121.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.otus.l121.cache.*;
import ru.otus.l121.dataset.*;

class TestCache {

	UserDataSet userOne, userTwo;
	AddressDataSet address;
	PhoneDataSet phone;

	Cache<DataSetKey, DataSet> cache;

	@BeforeEach
	void setUp() throws Exception {
		userOne = new UserDataSet(1L, "Test User 1", 35);
		userTwo = new UserDataSet(2L, "Test User 2", 30);
		address = new AddressDataSet(1L, "Green Lane");
		phone = new PhoneDataSet(1L, "0147258369");
	}

	@AfterEach
	void tearDown() throws Exception {
		userOne = null;
		userTwo = null;
		address = null;
		phone = null;

		if (cache != null) {
			cache.dispose();
		}
		cache = null;
	}

	@Test
	void testFoundInCache() {
		cache = CacheFactory.getCache(new CacheConfiguration("cacheEternal.properties"));
		cache.put(new Element<>(new DataSetKey(userOne.getClass(), userOne.getId()), userOne));

		DataSet theUser = cache.get(new DataSetKey(UserDataSet.class, 1L)).getValue();
		assertEquals(userOne, theUser);
	}

	@Test
	void testNotFoundInCache() {
		cache = CacheFactory.getCache(new CacheConfiguration("cacheEternal.properties"));
		cache.put(new Element<>(new DataSetKey(userOne.getClass(), userOne.getId()), userOne));

		assertNull(cache.get(new DataSetKey(UserDataSet.class, 2L)));
	}

	@Test
	void testMaxCapacity2() {
		cache = CacheFactory.getCache(new CacheConfiguration("cacheMaxCapacity2.properties"));
		cache.put(new Element<>(new DataSetKey(userOne.getClass(), userOne.getId()), userOne));
		cache.put(new Element<>(new DataSetKey(address.getClass(), address.getId()), address));
		cache.put(new Element<>(new DataSetKey(phone.getClass(), phone.getId()), phone));

		// Max capacity = 2, first inserted entry should be evicted
		assertNull(cache.get(new DataSetKey(UserDataSet.class, 1L)));

		// Max capacity = 2, second inserted entry should be in cache
		DataSet theAddress = cache.get(new DataSetKey(address.getClass(), address.getId())).getValue();
		assertEquals(address, theAddress);

		// Max capacity = 2, third inserted entry should be in cache
		DataSet thePhone = cache.get(new DataSetKey(phone.getClass(), phone.getId())).getValue();
		assertEquals(phone, thePhone);
	}

	@Test
	void testLifeTime3s() throws InterruptedException {
		cache = CacheFactory.getCache(new CacheConfiguration("cacheLifeTime3s.properties"));
		cache.put(new Element<>(new DataSetKey(address.getClass(), address.getId()), address));

		Thread.sleep(1000);

		// Entry should be in cache
		DataSet theAddress = cache.get(new DataSetKey(address.getClass(), address.getId())).getValue();
		assertEquals(address, theAddress);

		Thread.sleep(2000);

		// Entry should be evicted after 3s from creation
		assertNull(cache.get(new DataSetKey(address.getClass(), address.getId())));
	}

	@Test
	void testIdleTime2s() throws InterruptedException {
		cache = CacheFactory.getCache(new CacheConfiguration("cacheIdleTime2s.properties"));
		cache.put(new Element<>(new DataSetKey(phone.getClass(), phone.getId()), phone));

		// Entry should be in cache
		DataSet thePhone = cache.get(new DataSetKey(phone.getClass(), phone.getId())).getValue();
		assertEquals(phone, thePhone);

		Thread.sleep(2005);

		// Entry should be evicted after 2s from last access
		assertNull(cache.get(new DataSetKey(phone.getClass(), phone.getId())));
	}
}
