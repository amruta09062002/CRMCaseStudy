package com.crm.marketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.marketing.entities.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer>{
	
}
