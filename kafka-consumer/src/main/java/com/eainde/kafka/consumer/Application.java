package com.eainde.kafka.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.schema.client.ConfluentSchemaRegistryClient;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;
import org.springframework.cloud.stream.schema.client.SchemaRegistryClient;

@SpringBootApplication
@EnableSchemaRegistryClient
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  static class ConfluentSchemaRegistryConfiguration {
    public SchemaRegistryClient schemaRegistryClient(
        @Value("${spring.cloud.stream.schemaRegistryClient.endpoint}") String endPoint) {
      ConfluentSchemaRegistryClient client = new ConfluentSchemaRegistryClient();
      client.setEndpoint(endPoint);
      return client;
    }
  }
}
