package com.mounish.service;

import java.util.List;

import com.mounish.service.model.Creative;

/**
 * Interface for Creative related service methods
 * @author Mounish Savier
 *
 */
public interface CreativeService {
	
	/**
	 * Method to store all the activity data into the data base
	 * 
	 * @param compaigns
	 * @return
	 */
	public boolean addCreatives(List<Creative> activities);

}
