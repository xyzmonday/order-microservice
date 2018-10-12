package com.yff.order.restapi.controller;

import com.alibaba.fastjson.JSON;
import com.yff.order.domain.entity.Order;
import com.yff.order.restapi.mqchannel.OutputSource;
import com.yff.order.domain.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/order")
@EnableBinding(OutputSource.class)
public class OrderRestController {

    private static final Logger logger = LoggerFactory.getLogger(OrderRestController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    @Output(OutputSource.ORDERSCHANNEL)
    private MessageChannel messageChannel;


    @RequestMapping(value = "/{id}")
    public CompletableFuture<String> findById(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(() -> {
            Order order = orderService.getOne(id);
            return JSON.toJSONString(order);
        });
    }

    @RequestMapping(value = "/mono/{id}")
    @ResponseBody
    public Mono<Order> findOneById(@PathVariable Long id) {
        return Mono.just(id).map(e -> orderService.getOne(e));
    }


    @GetMapping("/test/{id}")
    public Order test(@PathVariable Long id) {
        Order order = orderService.getOne(id);
        return order;
    }

}
