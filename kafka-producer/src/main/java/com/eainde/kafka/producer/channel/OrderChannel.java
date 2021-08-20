package com.eainde.kafka.producer.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OrderChannel {
  @Output("output-order")
  MessageChannel output();
}
