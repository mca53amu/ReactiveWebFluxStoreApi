package com.store.mobile.api.repository;

import static io.netty.util.internal.StringUtil.isNullOrEmpty;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.store.mobile.api.entity.Mobile;

import reactor.core.publisher.Flux;

/**
 * Returns the list of all Mobiles based on filter criteria applied
 * 
 * @author mohammad.miyan
 *
 */
@Repository
public class MobileCustomRepositoryImpl implements MobileCustomRepository {

	@Autowired
	ReactiveMongoOperations reactiveMongoTemplate;

	public Flux<Mobile> findMobileByProperties(Long id, String brand, String picture, String announceDate,
			Long priceEur, String sim, String resolution, String audioJack, String gps, String battery, String sortBy,
			String direction) {
		Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
		final Query query = new Query().with(sort);
		final List<Criteria> criteria = new ArrayList<>();
		if (null != id) {
			criteria.add(Criteria.where("id").is(id));
		}

		if (!isNullOrEmpty(brand)) {
			criteria.add(Criteria.where("brand").regex(brand, "i"));
		}

		if (!isNullOrEmpty(picture)) {
			criteria.add(Criteria.where("picture").is(picture));
		}

		if (!isNullOrEmpty(announceDate)) {
			criteria.add(Criteria.where("release.announceDate").is(announceDate));
		}

		if (null != priceEur) {
			criteria.add(Criteria.where("release.priceEur").is(priceEur));
		}

		if (!isNullOrEmpty(sim)) {
			criteria.add(Criteria.where("sim").regex(".*" + sim + ".*", "i"));
		}

		if (!isNullOrEmpty(resolution)) {
			criteria.add(Criteria.where("resolution").is(resolution));
		}

		if (!isNullOrEmpty(audioJack)) {
			criteria.add(Criteria.where("hardware.audioJack").regex(audioJack, "i"));
		}

		if (!isNullOrEmpty(gps)) {
			criteria.add(Criteria.where("hardware.gps").regex(".*" + gps + ".*", "i"));
		}

		if (!isNullOrEmpty(battery)) {
			criteria.add(Criteria.where("hardware.battery").is(battery));
		}
		query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
		Flux<Mobile> list = reactiveMongoTemplate.find(query, Mobile.class);
		return list;
	}

}
