package com.crm.marketing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.marketing.entities.Campaign;
import com.crm.marketing.repository.CampaignRepository;

@Service
public class CampaignServiceImpl implements CampaignService {
	
	@Autowired
	CampaignRepository campaignRepo;

	@Override
	public Campaign createCampaign(Campaign campaign) {
		
		return campaignRepo.save(campaign);
	}

	@Override
	public List<Campaign> getAllCampaigns() {
		return campaignRepo.findAll();
	}

}
