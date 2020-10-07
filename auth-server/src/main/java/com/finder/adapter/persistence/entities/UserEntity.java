package com.finder.adapter.persistence.entities;

import com.finder.domain.Permission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usr")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String nickname;
    private String imageUrl;
    private boolean active;
    private boolean blocked;

    @ElementCollection(targetClass = Permission.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_permission", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Permission> permissions = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_group",
            joinColumns = { @JoinColumn(name = "fk_user") },
            inverseJoinColumns = { @JoinColumn(name = "fk_group") })
    private Set<GroupEntity> groups = new HashSet<>();

}
