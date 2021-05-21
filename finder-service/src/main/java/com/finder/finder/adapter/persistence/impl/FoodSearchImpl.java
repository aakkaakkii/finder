package com.finder.finder.adapter.persistence.impl;

import com.finder.finder.adapter.persistence.mappers.FoodMapper;
import com.finder.finder.adapter.persistence.repository.search.FoodSearchRepository;
import com.finder.finder.domain.Food;
import com.finder.finder.port.out.FoodSearchPort;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class FoodSearchImpl implements FoodSearchPort {
    private final FoodSearchRepository foodSearchRepository;
    private final FoodMapper foodMapper;

    @Override
    public List<Food> search(String query) {

        SearchQuery q = new NativeSearchQuery(
                QueryBuilders.multiMatchQuery(query, "name", "description").minimumShouldMatch("66%")
        );

        return StreamSupport
                .stream(foodSearchRepository.search(q).spliterator(), false)
                .map(foodMapper::toDomain)
                .collect(Collectors.toList());
    }
}
