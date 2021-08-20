package com.eainde.kafka.producer.controller;

import com.eainde.kafka.producer.controller.dto.OrderRequestDto;
import com.eainde.kafka.producer.controller.dto.mapper.OrderRequestMapper;
import com.eainde.kafka.producer.producer.OrderProducer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderProducer producer;
    private final OrderRequestMapper mapper;

    OrderController(final OrderProducer producer,
                    final OrderRequestMapper mapper){
        this.producer=producer;
        this.mapper=mapper;
    }

    @PostMapping(value = "/")
    public ResponseEntity<Boolean> sendMessage(@RequestBody OrderRequestDto dto){
        return new ResponseEntity<>(producer.sendMessage(mapper.mapToRecord(dto)), HttpStatus.OK);
    }
}
