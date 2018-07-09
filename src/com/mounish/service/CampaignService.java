package com.mounish.service;

import java.util.List;
import com.mounish.service.model.Campaign;

/**
 * Interface for Campaign related service methods
 * @author Mounish Savier
 *
 */
public interface CampaignService {
	/**
	 * Method to store all the campaign data into the data base
	 * 
	 * @param compaigns
	 * @return
	 */
	public boolean addCampaigns(List<Campaign> compaigns);
}
