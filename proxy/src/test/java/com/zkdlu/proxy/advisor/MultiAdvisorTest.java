package com.zkdlu.proxy.advisor;

import com.zkdlu.proxy.common.service.ServiceImpl;
import com.zkdlu.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class MultiAdvisorTest {
    @Test
    @DisplayName("여러 프록시")
    void multiAdvisorTest1() {
        // client -> proxy2(advisor2) -> proxy1(advisor1) -> target

        // proxy1
        ServiceInterface target = new ServiceImpl();
        ProxyFactory factory1 = new ProxyFactory(target);
        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
        factory1.addAdvisor(advisor1);
        ServiceInterface proxy1 = (ServiceInterface) factory1.getProxy();

        // proxy2
        ProxyFactory factory2 = new ProxyFactory(proxy1);
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());
        factory2.addAdvisor(advisor2);
        ServiceInterface proxy2 = (ServiceInterface) factory2.getProxy();

        proxy2.save();
    }

    @Test
    @DisplayName("여러 프록시")
    void multiAdvisorTest2() {
        // client -> proxy -> advisor2 -> advisor1 -> target

        // proxy1
        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());

        ServiceInterface target = new ServiceImpl();
        ProxyFactory factory1 = new ProxyFactory(target);

        factory1.addAdvisor(advisor2);
        factory1.addAdvisor(advisor1);
        ServiceInterface proxy = (ServiceInterface) factory1.getProxy();

        proxy.save();
    }

    @Slf4j
    static class Advice1 implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice1 호출");
            return invocation.proceed();
        }
    }


    @Slf4j
    static class Advice2 implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice2 호출");
            return invocation.proceed();
        }
    }
}
