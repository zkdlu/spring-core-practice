package com.zkdlu.proxy.config.v2_dynamicproxy;

import com.zkdlu.proxy.app.v1.OrderControllerV1;
import com.zkdlu.proxy.app.v1.OrderControllerV1Impl;
import com.zkdlu.proxy.app.v1.OrderRepositoryV1;
import com.zkdlu.proxy.app.v1.OrderRepositoryV1Impl;
import com.zkdlu.proxy.app.v1.OrderServiceV1;
import com.zkdlu.proxy.app.v1.OrderServiceV1Impl;
import com.zkdlu.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import com.zkdlu.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyBasicConfig {
    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace trace) {
        OrderControllerV1 orderControllerV1 = new OrderControllerV1Impl(orderServiceV1(trace));
        OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class},
                new LogTraceBasicHandler(orderControllerV1, trace));

        return proxy;
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace trace) {
        OrderServiceV1 orderServiceV1 = new OrderServiceV1Impl(orderRepositoryV1(trace));

        OrderServiceV1 proxy = (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
                new Class[]{OrderServiceV1.class},
                new LogTraceBasicHandler(orderServiceV1, trace));

        return proxy;
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace trace) {
        OrderRepositoryV1 orderRepositoryV1 = new OrderRepositoryV1Impl();

        OrderRepositoryV1 proxy = (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(),
                new Class[]{OrderRepositoryV1.class},
                new LogTraceBasicHandler(orderRepositoryV1, trace));

        return proxy;
    }
}
