package com.finder.finder.adapter.persistence.repository;

import com.finder.finder.adapter.persistence.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    @Query(
            value = "SELECT r FROM RestaurantEntity r JOIN r.foods WHERE r.id=:foodId"
    )
    RestaurantEntity getByFoodId(@Param("foodId") Long foodId);

}
