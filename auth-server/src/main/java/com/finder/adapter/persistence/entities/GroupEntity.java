package com.finder.adapter.persistence.entities;

import com.finder.domain.Permission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ElementCollection(targetClass = Permission.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "group_permission", joinColumns = @JoinColumn(name = "group_id"))
    @Enumerated(EnumType.STRING)
    private Set<Permission> permissions;
    @ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)
    private List<UserEntity> users = new ArrayList<>();
}
