package ru.otus.hw03;

public class GCInfo {

	private final String name;
	private final String type;
	private long totalCount;
	private long totalDuration;

	public GCInfo(String name, String type) {
		this.name = name;
		this.type = type; // could be used to identify young/old generation GC
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void addCount(long count) {
		totalCount += count;
	}

	public void addCount() {
		addCount(1);
	}

	public long getTotalDuration() {
		return totalDuration;
	}

	public void addDuration(long duration) {
		totalDuration += duration;
	}

	public void reset() {
		totalCount = 0;
		totalDuration = 0;
	}

	@Override
	public String toString() {
		return name + " (" + type + "): " + totalCount + " collection(s) took " + totalDuration + " ms";
	}

}
