package com.zkdlu.proxy.config;

import com.zkdlu.proxy.app.v1.OrderControllerV1;
import com.zkdlu.proxy.app.v1.OrderControllerV1Impl;
import com.zkdlu.proxy.app.v1.OrderRepositoryV1;
import com.zkdlu.proxy.app.v1.OrderRepositoryV1Impl;
import com.zkdlu.proxy.app.v1.OrderServiceV1;
import com.zkdlu.proxy.app.v1.OrderServiceV1Impl;
import org.springframework.context.annotation.Bean;

public class AppV1Config {

    @Bean
    public OrderControllerV1 orderControllerV1() {
        return new OrderControllerV1Impl(orderServiceV1());
    }

    @Bean
    public OrderServiceV1 orderServiceV1() {
        return new OrderServiceV1Impl(orderRepositoryV1());
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1() {
        return new OrderRepositoryV1Impl();
    }
}
