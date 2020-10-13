package com.finder.finder.port.in;

import com.finder.finder.domain.Restaurant;
import com.finder.finder.port.models.RestaurantRequestModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> loadAll(int page, int limit);

    List<Restaurant> loadActive(int page, int limit);

    Restaurant getRestaurantByFood(Long id);

    Restaurant getById(Long id);

    Restaurant add(RestaurantRequestModel restaurant, MultipartFile[] files, Long initiatorId);

    Restaurant update(Long id, RestaurantRequestModel restaurant, MultipartFile[] files, Long initiatorId);

    Restaurant privilegeUpdate(Long id, RestaurantRequestModel restaurant, MultipartFile[] files);

    Restaurant disable(Long id, Long initiatorId);

    void delete(Long id);
}
