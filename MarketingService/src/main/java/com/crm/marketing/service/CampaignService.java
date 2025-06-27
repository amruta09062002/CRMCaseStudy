package com.crm.marketing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.marketing.entities.Campaign;

@Service
public interface CampaignService {
	
	Campaign createCampaign(Campaign campaign);
	List<Campaign> getAllCampaigns();

}
