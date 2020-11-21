package com.store.mobile.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.mobile.api.entity.Mobile;
import com.store.mobile.api.enums.SortBy;
import com.store.mobile.api.service.MobileService;

import reactor.core.publisher.Flux;

@RestController
public class HandsetStoreController {
	@Autowired
	MobileService mobileService;

	/**
	 * Filter Mobile list based on the request parameters
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param id
	 * @param brand
	 * @param picture
	 * @param announceDate
	 * @param priceEur
	 * @param sim
	 * @param resolution
	 * @param audioJack
	 * @param gps
	 * @param battery
	 * @return List of Mobiles
	 */
	@GetMapping("/mobile/search")
	public Flux<Mobile> mobileSearch(@RequestParam(required = false, defaultValue = "1") int currentPage, //
			@RequestParam(required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "sortBy", required = false, defaultValue = "ID") SortBy sortColumn, //
			@RequestParam(value = "sortDirection", required = false, defaultValue = "ASC") String sortDirection, //
			@RequestParam(required = false) Long id, @RequestParam(required = false) String brand,
			@RequestParam(required = false) String picture, @RequestParam(required = false) String announceDate,
			@RequestParam(required = false) Long priceEur, @RequestParam(required = false) String sim,
			@RequestParam(required = false) String resolution, @RequestParam(required = false) String audioJack,
			@RequestParam(required = false) String gps, @RequestParam(required = false) String battery) {

		Flux<Mobile> list = mobileService.fetchMobileByProperties(id, brand, picture, announceDate, priceEur, sim,
				resolution, audioJack, gps, battery, sortColumn.property, sortDirection);
		return list;
	}

}
