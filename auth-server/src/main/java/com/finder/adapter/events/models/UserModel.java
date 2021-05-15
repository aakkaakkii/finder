package com.finder.adapter.events.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserModel {
    private String type;
    private String action;
    private String username;
    private String mail;
}
