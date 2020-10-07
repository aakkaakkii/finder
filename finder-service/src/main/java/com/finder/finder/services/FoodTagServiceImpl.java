package com.finder.finder.services;

import com.finder.finder.domain.FoodTag;
import com.finder.finder.port.in.FoodTagService;
import com.finder.finder.port.out.FoodTagPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodTagServiceImpl implements FoodTagService {
    private final FoodTagPort foodTagPort;

    @Override
    public List<FoodTag> getAll() {
        return foodTagPort.loadAll();
    }

    @Override
    public FoodTag getById(Long id) {
        return foodTagPort.getById(id);
    }

    @Override
    public FoodTag add(FoodTag foodTag) {
        return foodTagPort.add(foodTag);
    }

    @Override
    public FoodTag update(FoodTag foodTag) {
        return foodTagPort.add(foodTag);
    }

    @Override
    public void delete(Long id) {
        foodTagPort.delete(id);
    }
}
