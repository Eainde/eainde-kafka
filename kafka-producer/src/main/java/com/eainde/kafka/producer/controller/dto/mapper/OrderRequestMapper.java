package com.eainde.kafka.producer.controller.dto.mapper;

import com.eainde.kafka.OrderRecord;
import com.eainde.kafka.producer.controller.dto.OrderRequestDto;
import org.springframework.stereotype.Component;

@Component
public class OrderRequestMapper {

    public OrderRecord mapToRecord(OrderRequestDto dto){
    return OrderRecord.newBuilder()
        .setId(dto.getId())
        .setAmount(dto.getAmount())
        .setDescription(dto.getDescription())
        .setNoOfItems(dto.getNoOfItems())
        .build();
    }
}
