package com.store.mobile.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.store.mobile.api.entity.Mobile;
import com.store.mobile.api.repository.MobileRepository;

import reactor.core.publisher.Flux;

/**
 *Fetch all Mobiles based on the properties
 * 
 * @author mohammad.miyan
 *
 */
@Service
public class MobileService {
	@Autowired
	MobileRepository mobileRepository;

	public Flux<Mobile> fetchMobileByProperties(Long id, String brand, String picture, String announceDate,
			Long priceEur, String sim, String resolution, String audioJack, String gps, String battery, String sortBy, String direction) {

		return mobileRepository.findMobileByProperties(id, brand, picture, announceDate, priceEur, sim, resolution,
				audioJack, gps, battery,sortBy,direction);
	}

}
