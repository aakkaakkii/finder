package com.finder.adapter.events.source;

import com.finder.adapter.events.CustomChannels;
import com.finder.adapter.events.models.UserModel;
import com.finder.domain.User;
import com.finder.port.out.EventPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
@EnableBinding(CustomChannels.class)
public class SimpleSourceBean implements EventPort {
    private final CustomChannels source;

    public void publishUserEvent(EventType action, User user) {
        log.info("Sending kafka message {} for user {}", action, user.getUsername());

        UserModel userModel = UserModel.builder()
                .type(User.class.getTypeName())
                .action(action.toString())
                .username(user.getUsername())
                .mail(user.getEmail())
                .build();

        source.output().send(MessageBuilder.withPayload(userModel).build());
    }
}
