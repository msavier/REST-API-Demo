package com.mounish.service.model;

/**
 * POJO Class to hold Campaign data
 * @author Mounish Savier
 *
 */
public class Campaign {

	private int id;
	private double cpm;
	private String name;
	private String startDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCpm() {
		return cpm;
	}

	public void setCpm(double cpm) {
		this.cpm = cpm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

}
