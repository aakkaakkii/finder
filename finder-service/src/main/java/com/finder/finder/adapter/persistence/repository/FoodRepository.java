package com.finder.finder.adapter.persistence.repository;

import com.finder.finder.adapter.persistence.entities.FoodEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Long> {
    @Query(
            value = "SELECT f FROM FoodEntity f WHERE f.isActive=true"
    )
    List<FoodEntity> loadActive(Pageable pageable);
    @Query(
            value = "SELECT f FROM FoodEntity f WHERE f.id NOT IN :ids AND f.isActive=true"
    )
    List<FoodEntity> findActiveExcludeByIds(@Param("ids") List<Long> ids, Pageable pageable);

    @Query(
            value = "SELECT f FROM FoodEntity f JOIN f.foodTags t WHERE t.id=:tagId AND  f.isActive=true"
    )
    List<FoodEntity> loadActiveFoodByTeg(@Param("tagId") Long groupId, Pageable pageable);
}
