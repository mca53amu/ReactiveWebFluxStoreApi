package com.store.mobile.api.repository;

import com.store.mobile.api.entity.Mobile;

import reactor.core.publisher.Flux;

/**
 * @author mohammad.miyan
 *
 */
public interface MobileCustomRepository {
	public Flux<Mobile> findMobileByProperties(Long id, String brand, String picture, String announceDate,
			Long priceEur, String sim, String resolution, String audioJack, String gps, String battery, String sortBy,String direction);
}
