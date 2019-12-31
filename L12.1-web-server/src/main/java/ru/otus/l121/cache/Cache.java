package ru.otus.l121.cache;

import java.util.Properties;

public interface Cache<K, V> {

	void put(Element<K, V> element);

	Element<K, V> get(K key);

	boolean remove(K key);

	int getHitCount();

	int getMissCount();

	int getSize();

	Properties getProperties();

	void dispose();

}
