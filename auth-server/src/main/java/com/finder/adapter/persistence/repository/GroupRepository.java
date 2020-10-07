package com.finder.adapter.persistence.repository;

import com.finder.adapter.persistence.entities.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
}
