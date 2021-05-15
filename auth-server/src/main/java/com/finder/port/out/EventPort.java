package com.finder.port.out;

import com.finder.domain.User;

public interface EventPort {
    enum EventType {
        ADD, UPDATE, GET, DELETE
    }

    void publishUserEvent(EventType action, User user);
}
