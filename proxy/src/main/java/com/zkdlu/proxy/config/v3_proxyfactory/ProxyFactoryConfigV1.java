package com.zkdlu.proxy.config.v3_proxyfactory;

import com.zkdlu.proxy.app.v1.OrderControllerV1;
import com.zkdlu.proxy.app.v1.OrderControllerV1Impl;
import com.zkdlu.proxy.app.v1.OrderRepositoryV1;
import com.zkdlu.proxy.app.v1.OrderRepositoryV1Impl;
import com.zkdlu.proxy.app.v1.OrderServiceV1;
import com.zkdlu.proxy.app.v1.OrderServiceV1Impl;
import com.zkdlu.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import com.zkdlu.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;

@Slf4j
public class ProxyFactoryConfigV1 {

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace trace) {
        OrderControllerV1 orderController = new OrderControllerV1Impl(orderServiceV1(trace));

        ProxyFactory factory = new ProxyFactory(orderController);
        factory.addAdvisor(getAdvisor(trace));
        OrderControllerV1 proxy = (OrderControllerV1) factory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderController.getClass());
        return proxy;
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace trace) {
        OrderServiceV1 orderService = new OrderServiceV1Impl(orderRepositoryV1(trace));

        ProxyFactory factory = new ProxyFactory(orderService);
        factory.addAdvisor(getAdvisor(trace));
        OrderServiceV1 proxy = (OrderServiceV1) factory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderService.getClass());
        return proxy;
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace trace) {
        OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();

        ProxyFactory factory = new ProxyFactory(orderRepository);
        factory.addAdvisor(getAdvisor(trace));
        OrderRepositoryV1 proxy = (OrderRepositoryV1) factory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderRepository.getClass());
        return proxy;
    }

    private Advisor getAdvisor(LogTrace trace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        LogTraceAdvice advice = new LogTraceAdvice(trace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }

}
