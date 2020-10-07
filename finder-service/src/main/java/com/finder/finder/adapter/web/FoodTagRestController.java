package com.finder.finder.adapter.web;

import com.finder.finder.domain.FoodTag;
import com.finder.finder.port.in.FoodTagService;
import com.finder.finder.port.models.FoodTagRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/v1/foodTags")
@RequiredArgsConstructor
public class FoodTagRestController {
    private final FoodTagService foodTagService;

    @GetMapping
    public List<FoodTag> load() {
        return foodTagService.load();
    }

    @GetMapping("/{id}")
    public FoodTag getById(@PathVariable Long id) {
        return foodTagService.getById(id);
    }

    @PostMapping
    public FoodTag add(@RequestBody FoodTagRequest foodTagRequest) {
        return foodTagService.add(foodTagRequest);
    }

    @PutMapping("/{id}")
    public FoodTag update(@PathVariable Long id, @RequestBody FoodTagRequest foodTagRequest) {
        return foodTagService.update(foodTagRequest, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        foodTagService.delete(id);
    }
}
