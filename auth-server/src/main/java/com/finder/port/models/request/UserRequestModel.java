package com.finder.port.models.request;

import com.finder.domain.Permission;

import java.util.List;
import java.util.Set;

public class UserRequestModel {
    public String username;
    public String password;
    public String email;
    public String nickname;
    public boolean active;
    public boolean blocked;
    public Set<Permission> permissions;
    public List<Long> groupsIds;
}
