package com.finder.adapter.persistence.repository;

import com.finder.adapter.persistence.entities.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
    @Query(
            value = "SELECT u FROM UserEntity u JOIN u.groups g WHERE g.id=:groupId"
    )
    List<UserEntity> findUsersByGroupId(@Param("groupId") Long groupId, Pageable pageable);
}