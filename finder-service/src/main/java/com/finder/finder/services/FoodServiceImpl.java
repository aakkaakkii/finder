package com.finder.finder.services;

import com.finder.finder.domain.Food;
import com.finder.finder.domain.FoodTag;
import com.finder.finder.domain.Restaurant;
import com.finder.finder.port.in.FoodService;
import com.finder.finder.port.models.FoodRequestModel;
import com.finder.finder.port.out.FileStoragePort;
import com.finder.finder.port.out.FoodPort;
import com.finder.finder.port.out.FoodTagPort;
import com.finder.finder.port.out.RestaurantPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
    private final FoodPort foodPort;
    private final FoodTagPort foodTagPort;
    private final RestaurantPort restaurantPort;
    private final FileStoragePort fileStoragePort;

    @Override
    public List<Food> load(int page, int limit) {
        return foodPort.load(page, limit);
    }

    @Override
    public List<Food> loadActive(int page, int limit) {
        return foodPort.loadActive(page, limit);
    }

    @Override
    public List<Food> getRandom5ActiveFoodExcludeByIds(List<Long> ids) {
        return foodPort.getRandom5ActiveFoodExcludeByIds(ids);
    }

    @Override
    public List<Food> loadActiveByTag(Long tagId, int page, int limit) {
        return foodPort.loadActiveFoodByTeg(tagId, page, limit);
    }

    @Override
    public Food getById(Long id) {
        return foodPort.getById(id);
    }

    @Override
    public Food add(FoodRequestModel food, MultipartFile file, Long restaurantId, Long initiatorId) {
        Restaurant restaurant = restaurantPort.getById(restaurantId);
        Food savedFood = null;

        if(restaurant.getCreatorId().equals(initiatorId)) {
            List<FoodTag> foodTags = foodTagPort.loadAllByIds(food.foodTags);
            String imagePath = fileStoragePort.addFoodImage(file, restaurantId);

            savedFood = foodPort.add(Food.builder()
                    .foodTags(foodTags)
                    .description(food.description)
                    .name(food.name)
                    .price(food.price)
                    .imagePath(imagePath)
                    .isActive(true)
                    .build()
            );
            restaurant.addFood(savedFood);
            restaurantPort.update(restaurant);
        }

        return savedFood;
    }

    @Override
    //TODO: different foodRequestModel
    public Food update(Long id, FoodRequestModel food, MultipartFile file, Long restaurantId, Long initiatorId) {
        Restaurant restaurant = restaurantPort.getById(restaurantId);
        Food foodFromDb = foodPort.getById(id);

        if(restaurant.getCreatorId().equals(initiatorId)) {
            updateFood(food, file, foodFromDb, restaurantId);
        }
        return foodFromDb;
    }

    @Override
    public void delete(Long id, Long initiatorId) {
        //TODO: implement image delete
        Restaurant restaurant = restaurantPort.getByFoodId(id);

        if(restaurant.getCreatorId().equals(initiatorId)) {
            privilegeDelete(id);
        }
    }

    @Override
    public Food privilegeUpdate(Long id, FoodRequestModel food, MultipartFile file) {
        Food foodFromDb = foodPort.getById(id);
        Restaurant restaurant = restaurantPort.getByFoodId(id);

        return updateFood(food, file, foodFromDb, restaurant.getId());
    }

    @Override
    public void privilegeDelete(Long id) {
        Food food = foodPort.getById(id);
        fileStoragePort.deleteFoodImage(food.getImagePath());

        foodPort.delete(id);
    }

    private Food updateFood(FoodRequestModel food, MultipartFile file, Food foodFromDb, Long restaurantId) {

        List<FoodTag> foodTags = foodTagPort.loadAllByIds(food.foodTags);
        String imagePath = fileStoragePort.updateFoodImage(foodFromDb.getImagePath(), file, restaurantId);

        foodFromDb.setDescription(food.description);
        foodFromDb.setImagePath(imagePath);
        foodFromDb.setFoodTags(foodTags);
        foodFromDb.setPrice(food.price);
        foodFromDb.setName(food.name);
        //TODO: isActive change?
//        foodFromDb.isActive()

        return foodPort.update(foodFromDb);
    }
}
