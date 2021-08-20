package com.eainde.kafka.consumer.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import com.eainde.kafka.OrderRecord;

import java.util.function.Consumer;

@Component
public class OrderListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(OrderListener.class);

  @Bean
  public Consumer<Message<OrderRecord>> listen() {
    return input -> {
      var payload = input.getPayload();
      LOGGER.info(
          "Got message with description {}, id {}, no of items {}",
          payload.getDescription(),
          payload.getId(),
          payload.getNoOfItems());
      var headers= input.getHeaders();
      var acknowledgement= headers.get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
      if(acknowledgement != null){
        acknowledgement.acknowledge();
      }
    };
  }
}
