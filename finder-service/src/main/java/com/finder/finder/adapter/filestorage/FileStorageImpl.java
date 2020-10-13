package com.finder.finder.adapter.filestorage;

import com.finder.finder.port.out.FileStoragePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileStorageImpl implements FileStoragePort {
    private final FileStorageClient fileStorageClient;

    @Override
    public List<String> addRestaurantImages(MultipartFile[] files, Long restaurantId) {
        String path = getRestaurantGalleryPath(restaurantId);
        return fileStorageClient.saveFiles(path, files);
    }

    @Override
    public void deleteRestaurantImages(List<String> paths) {
        fileStorageClient.deleteFiles(paths.toArray(new String[0]));
    }

    @Override
    public String addFoodImage(MultipartFile file, Long restaurantId) {
        String path = getFoodFolderPath(restaurantId);
        return fileStorageClient.saveFile(path, file);
    }

    @Override
    public void deleteFoodImage(String path) {
        fileStorageClient.deleteFile(path);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {
        fileStorageClient.deleteFolder(getRestaurantPath(restaurantId));
    }

    @Override
    public String updateFoodImage(String imagePath, MultipartFile file, Long restaurantId) {
        return null;
    }

    private String getRestaurantGalleryPath(Long restaurantId) {
        return String.format("/%s/%s/%s/%s",
                IMAGE_FOLDER_NAME, RESTAURANT_FOLDER_NAME, restaurantId, RESTAURANT_GALLERY_FOLDER_NAME);
    }

    private String getRestaurantPath(Long restaurantId) {
        return String.format("/%s/%s/%s",
                IMAGE_FOLDER_NAME, RESTAURANT_FOLDER_NAME, restaurantId);
    }

    private String getFoodFolderPath(Long restaurantId) {
        return String.format("/%s/%s/%s/%s",
                IMAGE_FOLDER_NAME, RESTAURANT_FOLDER_NAME, restaurantId, FOOD_IMAGE_FOLDER_NAME);
    }
}
