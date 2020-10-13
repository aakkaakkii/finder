package com.finder.finder.adapter.web;

import com.finder.finder.domain.Food;
import com.finder.finder.port.in.FoodService;
import com.finder.finder.port.models.FoodRequestModel;
import com.finder.finder.utils.jwt.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/v1/foods")
@RequiredArgsConstructor
public class FoodRestService {
    private final FoodService foodService;

    @GetMapping
    //TODO: should be privilege? (only for admins)
    public List<Food> load(@RequestParam int page, @RequestParam int limit) {
        return foodService.load(page, limit);
    }

    @GetMapping("/active")
    public List<Food> loadActive(@RequestParam int page, @RequestParam int limit) {
        return foodService.loadActive(page, limit);
    }

    @GetMapping("/tag/{id}")
    public List<Food> loadByTagId(@PathVariable Long id, @RequestParam int page, @RequestParam int limit) {
        return foodService.loadActiveByTag(id, page, limit);
    }

    @GetMapping("/random")
    public List<Food> getRandom5FoodExcludeByIds(@RequestBody List<Long> ids) {
        return foodService.getRandom5ActiveFoodExcludeByIds(ids == null ? new ArrayList<>() : ids);
    }

    @GetMapping("/{id}")
    public Food getById(@PathVariable Long id) {
        return foodService.getById(id);
    }

    @PostMapping
    public Food create(@RequestBody FoodRequestModel food,
                       @RequestParam MultipartFile file,
                       Authentication authentication) {
        return foodService.add(food, file, food.restaurantId,  UserUtil.getUserIdFromAuthentication(authentication));
    }

    @PutMapping("/{id}")
    public Food update(@RequestBody FoodRequestModel food,
                       @RequestParam MultipartFile file,
                       @PathVariable Long id, Authentication authentication) {
        return foodService.update(id, food, file, food.restaurantId,  UserUtil.getUserIdFromAuthentication(authentication));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, Authentication authentication) {
        foodService.delete(id,  UserUtil.getUserIdFromAuthentication(authentication));
    }

    @PutMapping("/privilege/update/{id}")
    //TODO: only for admins
    public Food privilegeUpdate(@RequestBody FoodRequestModel food,
                                @RequestParam MultipartFile file,
                                @PathVariable Long id) {
        return foodService.privilegeUpdate(id, food, file);
    }

    @DeleteMapping("/privilege/delete/{id}")
    //TODO: only for admins
    public void privilegeDelete(@PathVariable Long id) {
        foodService.privilegeDelete(id);
    }
}
