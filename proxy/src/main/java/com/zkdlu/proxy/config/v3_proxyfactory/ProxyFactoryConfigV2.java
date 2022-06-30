package com.zkdlu.proxy.config.v3_proxyfactory;

import com.zkdlu.proxy.app.v2.OrderControllerV2;
import com.zkdlu.proxy.app.v2.OrderRepositoryV2;
import com.zkdlu.proxy.app.v2.OrderServiceV2;
import com.zkdlu.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import com.zkdlu.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;

@Slf4j
public class ProxyFactoryConfigV2 {

    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace trace) {
        OrderControllerV2 orderController = new OrderControllerV2(orderServiceV2(trace));

        ProxyFactory factory = new ProxyFactory(orderController);
        factory.addAdvisor(getAdvisor(trace));
        OrderControllerV2 proxy = (OrderControllerV2) factory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderController.getClass());
        return proxy;
    }

    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace trace) {
        OrderServiceV2 orderService = new OrderServiceV2(orderRepositoryV2(trace));

        ProxyFactory factory = new ProxyFactory(orderService);
        factory.addAdvisor(getAdvisor(trace));
        OrderServiceV2 proxy = (OrderServiceV2) factory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderService.getClass());
        return proxy;
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace trace) {
        OrderRepositoryV2 orderRepository = new OrderRepositoryV2();

        ProxyFactory factory = new ProxyFactory(orderRepository);
        factory.addAdvisor(getAdvisor(trace));
        OrderRepositoryV2 proxy = (OrderRepositoryV2) factory.getProxy();
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
