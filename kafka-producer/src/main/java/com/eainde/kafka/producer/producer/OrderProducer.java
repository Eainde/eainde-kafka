package com.eainde.kafka.producer.producer;

import com.eainde.kafka.OrderRecord;
import com.eainde.kafka.producer.channel.OrderChannel;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrderProducer {
  private final String orderTopicName;
  private final OrderChannel mOrderChannel;
  private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);

  OrderProducer(
      @Value("${spring.cloud.stream.bindings.output-order.destination}")
          final String orderTopicName,
      final OrderChannel mOrderChannel) {
    this.orderTopicName = orderTopicName;
    this.mOrderChannel = mOrderChannel;
  }

  public boolean sendMessage(OrderRecord record) {
    boolean status = false;
    try {
      final MessageChannel messageChannel = mOrderChannel.output();
      status =
          messageChannel.send(
              MessageBuilder.withPayload(record)
                  .setHeader(MessageHeaders.CONTENT_TYPE, "application/**avro")
                  .setHeader(KafkaHeaders.TOPIC, orderTopicName)
                  .setHeader("X-Idempotency-Key", UUID.randomUUID())
                  .build());
      LOGGER.info(
          "Order record(Id: {}, Description: {}) has been pushed to the topic {}.",
          record.getId(),
          record.getDescription(),
          orderTopicName);

    } catch (Exception e) {
      LOGGER.error("Error in pushing Order record. ", e);
    }
    return status;
  }
}
