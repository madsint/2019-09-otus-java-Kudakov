package ru.otus.l121.cache;

public class Element<K, V> {

	private final K key;
	private final V value;
	private final long creationTime;
	private long lastAccessTime;

	public Element(K key, V value) {
		this.key = key;
		this.value = value;
		this.creationTime = getCurrentTime();
	}

	public long getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(long lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	public long getCreationTime() {
		return creationTime;
	}

	@Override
	public String toString() {
		return key + ": " + value + ", created: " + creationTime + ", last accessed: " + lastAccessTime;
	}

	private long getCurrentTime() {
		return System.currentTimeMillis();
	}
}
