package com.zkdlu.proxy.config.v1_proxy;

import com.zkdlu.proxy.app.v1.OrderControllerV1;
import com.zkdlu.proxy.app.v1.OrderControllerV1Impl;
import com.zkdlu.proxy.app.v1.OrderRepositoryV1;
import com.zkdlu.proxy.app.v1.OrderRepositoryV1Impl;
import com.zkdlu.proxy.app.v1.OrderServiceV1;
import com.zkdlu.proxy.app.v1.OrderServiceV1Impl;
import com.zkdlu.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import com.zkdlu.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import com.zkdlu.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import com.zkdlu.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;

public class InterfaceProxyConfig {

    @Bean
    public OrderControllerV1 orderController(LogTrace trace) {
        return new OrderControllerInterfaceProxy(new OrderControllerV1Impl(orderService(trace)), trace);
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace trace) {
        return new OrderServiceInterfaceProxy(new OrderServiceV1Impl(orderRepository(trace)), trace);
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace trace) {
        return new OrderRepositoryInterfaceProxy(new OrderRepositoryV1Impl(), trace);
    }
}
