package com.finder.finder.services;

import com.finder.finder.domain.Restaurant;
import com.finder.finder.port.in.RestaurantService;
import com.finder.finder.port.models.RestaurantRequestModel;
import com.finder.finder.port.out.FileStoragePort;
import com.finder.finder.port.out.RestaurantPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantPort restaurantPort;
    private final FileStoragePort fileStoragePort;

    @Override
    public List<Restaurant> loadAll(int page, int limit) {
        return restaurantPort.loadAll(page, limit);
    }

    @Override
    public List<Restaurant> loadActive(int page, int limit) {
        return restaurantPort.loadActive(page, limit);
    }

    @Override
    public Restaurant getRestaurantByFood(Long id) {
        return restaurantPort.getByFoodId(id);
    }

    @Override
    public Restaurant getById(Long id) {
        return restaurantPort.getById(id);
    }

    @Override
    public Restaurant add(RestaurantRequestModel restaurant, MultipartFile[] files, Long initiatorId) {
        Restaurant res = restaurantPort.add(Restaurant.builder()
                .creatorId(initiatorId)
                .description(restaurant.description)
                .name(restaurant.name)
                .isActive(true)
                .build());

        List<String> imagePaths = fileStoragePort.addRestaurantImages(files, res.getId());

        res.setImagePaths(imagePaths);
        restaurantPort.update(res);

        return res;
    }

    @Override
    public Restaurant update(Long id, RestaurantRequestModel restaurant, MultipartFile[] files, Long initiatorId) {
        return null;
    }

    @Override
    public Restaurant privilegeUpdate(Long id, RestaurantRequestModel restaurant, MultipartFile[] files) {
        return null;
    }

    @Override
    public Restaurant disable(Long id, Long initiatorId) {
        Restaurant restaurant = restaurantPort.getById(id);
        if(!restaurant.getCreatorId().equals(initiatorId)){
            throw new AccessDeniedException("access denied");
        }
        restaurant.setActive(false);
        return restaurantPort.update(restaurant);
    }

    @Override
    public void delete(Long id) {
        fileStoragePort.deleteRestaurant(id);
        restaurantPort.delete(id);
    }
}
