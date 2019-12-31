package ru.otus.l121.cache;

public class CacheFactory {

	public static <K, V> Cache<K, V> getCache(CacheConfiguration configuration) {

		int maxCapacity = configuration.getMaxCapacity();
		long lifeTime = configuration.getLifeTime();
		long idleTime = configuration.getIdleTime();
		boolean isEternal = configuration.isEternal();

		return new CacheStore<K, V>(maxCapacity, lifeTime, idleTime, isEternal);
	}

}
