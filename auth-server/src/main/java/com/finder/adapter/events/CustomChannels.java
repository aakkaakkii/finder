package com.finder.adapter.events;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomChannels {
    @Output("output")
    MessageChannel output();
}
