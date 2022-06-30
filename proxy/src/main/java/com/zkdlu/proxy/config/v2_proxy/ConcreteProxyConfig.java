package com.zkdlu.proxy.config.v2_proxy;

import com.zkdlu.proxy.app.v2.OrderControllerV2;
import com.zkdlu.proxy.app.v2.OrderRepositoryV2;
import com.zkdlu.proxy.app.v2.OrderServiceV2;
import com.zkdlu.proxy.config.v2_proxy.concrete_proxy.OrderControllerConcreteProxy;
import com.zkdlu.proxy.config.v2_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import com.zkdlu.proxy.config.v2_proxy.concrete_proxy.OrderServiceConcreteProxy;
import com.zkdlu.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;

public class ConcreteProxyConfig {
    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace trace) {
        return new OrderControllerConcreteProxy(new OrderControllerV2(orderServiceV2(trace)), trace);
    }

    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace trace) {
        return new OrderServiceConcreteProxy(new OrderServiceV2(orderRepositoryV2(trace)), trace);
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace trace) {
        return new OrderRepositoryConcreteProxy(new OrderRepositoryV2(), trace);
    }
}
