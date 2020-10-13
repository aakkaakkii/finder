package com.finder.finder.port.out;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileStoragePort {
    String IMAGE_FOLDER_NAME = "images";
    String RESTAURANT_FOLDER_NAME = "restaurant";
    String RESTAURANT_GALLERY_FOLDER_NAME = "gallery";
    String FOOD_IMAGE_FOLDER_NAME = "food";

    List<String> addRestaurantImages(MultipartFile[] files, Long restaurantId);
    void deleteRestaurantImages(List<String> paths);
    String addFoodImage(MultipartFile file, Long restaurantId);
    void deleteFoodImage(String path);
    void deleteRestaurant(Long restaurantId);

    String updateFoodImage(String imagePath, MultipartFile file, Long restaurantId);
}
