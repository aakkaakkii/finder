package com.finder.finder.adapter.persistence.repository.search;

import com.finder.finder.adapter.persistence.entities.FoodEntity;
import com.finder.finder.domain.Food;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface FoodSearchRepository extends ElasticsearchRepository<FoodEntity, Long> {
}
