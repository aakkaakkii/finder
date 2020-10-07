package com.finder.domain;


import com.finder.utils.jwt.JwtTokenProvider;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String nickname;
    private String imageUrl;
    private boolean active;
    private boolean blocked;
    private Set<Permission> permissions = new HashSet<>();
    private Set<Group> groups = new HashSet<>();

    public List<String> showAllPermissionsAsList(){
        Set<String> result = new HashSet<>();

        for(Group group: this.getGroups()){
            for (Permission permission: group.getPermissions()){
                result.add(permission.getPermission());
            }
        }

        for (Permission permission: this.getPermissions()){
            result.add(permission.getPermission());
        }

        return Lists.newArrayList(result);
    }

    public String createAccessToken(){
        Map<String, Object> map = new HashMap<>();
        map.put("scope", showAllPermissionsAsList());
        map.put("userId", id);
        return JwtTokenProvider.createToken(username, map);
    }

    public String createRefreshToken(){
        return JwtTokenProvider.createRefreshToken(username);
    }

    public String createToken(){
        return JwtTokenProvider.createToken(username);
    }

}
