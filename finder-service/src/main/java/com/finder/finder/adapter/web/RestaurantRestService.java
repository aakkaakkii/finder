package com.finder.finder.adapter.web;

import com.finder.finder.domain.Restaurant;
import com.finder.finder.port.in.RestaurantService;
import com.finder.finder.port.models.RestaurantRequestModel;
import com.finder.finder.utils.jwt.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/rest/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantRestService {
    private final RestaurantService restaurantService;

    @GetMapping
    //TODO: should be privilege? (only for admins)
    public List<Restaurant> load(@RequestParam int page, @RequestParam int limit) {
        return restaurantService.loadAll(page, limit);
    }

    @GetMapping("/active")
    public List<Restaurant> loadActive(@RequestParam int page, @RequestParam int limit) {
        return restaurantService.loadActive(page, limit);
    }

    @GetMapping("/{id}")
    public Restaurant getById(@PathVariable Long id) {
        return restaurantService.getById(id);
    }

    @GetMapping("/food/{id}")
    public Restaurant getByFood(@PathVariable Long id) {
        return restaurantService.getRestaurantByFood(id);
    }


    @PostMapping
    public Restaurant create(@RequestBody RestaurantRequestModel restaurant,
                             @RequestParam MultipartFile[] files,
                             Authentication authentication) { ;
        return restaurantService.add(restaurant, files,  UserUtil.getUserIdFromAuthentication(authentication));
    }

    @PutMapping("/{id}")
    public Restaurant update(@RequestBody RestaurantRequestModel restaurant,
                             @RequestParam MultipartFile[] files,
                             @PathVariable Long id, Authentication authentication) {
        return restaurantService.update(id, restaurant, files,  UserUtil.getUserIdFromAuthentication(authentication));
    }

    @PutMapping("/disable/{id}")
    public Restaurant disable(@PathVariable Long id, Authentication authentication) {
        return restaurantService.disable(id, UserUtil.getUserIdFromAuthentication(authentication));
    }

    @PutMapping("/privilege/update/{id}")
    //TODO: only for admins
    public Restaurant privilegeUpdate(@RequestBody RestaurantRequestModel restaurant,
                                      @RequestParam MultipartFile[] file,
                                      @PathVariable Long id) {
        return restaurantService.privilegeUpdate(id, restaurant, file);
    }

    @DeleteMapping("/privilege/delete/{id}")
    //TODO: only for admins
    public void privilegeDelete(@PathVariable Long id) {
        restaurantService.delete(id);
    }

}
