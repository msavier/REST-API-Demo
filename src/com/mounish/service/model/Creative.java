package com.mounish.service.model;

/**
 * POJO Class to hold Creative data
 * @author Mounish Savier
 *
 */

public class Creative {

	private int id;
	private long clicks;
	private long views;	
	private int parentId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getClicks() {
		return clicks;
	}

	public void setClicks(long clicks) {
		this.clicks = clicks;
	}

	public long getViews() {
		return views;
	}

	public void setViews(long views) {
		this.views = views;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
}
