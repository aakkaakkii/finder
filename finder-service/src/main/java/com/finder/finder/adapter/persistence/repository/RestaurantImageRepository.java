package com.finder.finder.adapter.persistence.repository;

import com.finder.finder.adapter.persistence.entities.RestaurantImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantImageRepository extends JpaRepository<RestaurantImageEntity, Long> {
    RestaurantImageEntity getByImagePath(String path);
}
