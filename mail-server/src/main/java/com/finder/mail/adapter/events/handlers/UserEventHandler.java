package com.finder.mail.adapter.events.handlers;

import com.finder.mail.adapter.events.CustomChannels;
import com.finder.mail.adapter.events.models.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(CustomChannels.class)
@Slf4j
public class UserEventHandler {

    @StreamListener("inboundUser")
    public void logUserEvent(UserModel userModel) {
        log.info("Received a message of type {}", userModel.getType());

        switch (userModel.getAction()) {
            case "ADD":
                log.info("Received a Add message {} - {}", userModel.getUsername(), userModel.getMail());
                break;
            default:
                log.error("Received an UNKNOWN event from the organization service of type {}", userModel.getType());
                break;
        }
    }
}
