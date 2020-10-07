package com.finder.finder.adapter.persistence.repository;

import com.finder.finder.adapter.persistence.entities.FoodTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodTagRepository extends JpaRepository<FoodTagEntity, Long> {
}
