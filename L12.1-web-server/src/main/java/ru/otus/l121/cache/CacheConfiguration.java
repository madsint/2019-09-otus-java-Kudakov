package ru.otus.l121.cache;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CacheConfiguration {

	private static final String DEFAULT_RESOURCE_NAME = "cache.properties";

	private Properties configuration;

	public CacheConfiguration() {
		this(DEFAULT_RESOURCE_NAME);
	}

	public CacheConfiguration(String resourceName) {
		configuration = new Properties();
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceName)) {
			configuration.load(inputStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public int getMaxCapacity() {
		return Integer.parseInt(configuration.getProperty("maxCapacity", "0"));
	}

	public long getLifeTime() {
		return Long.parseLong(configuration.getProperty("lifeTime", "0"));
	}

	public long getIdleTime() {
		return Long.parseLong(configuration.getProperty("idleTime", "0"));
	}

	public boolean isEternal() {
		return Boolean.parseBoolean(configuration.getProperty("isEternal", "false"));
	}
}
