package com.zkdlu.proxy.advisor;

import com.zkdlu.proxy.common.advice.TimeAdvice;
import com.zkdlu.proxy.common.service.ServiceImpl;
import com.zkdlu.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;

@Slf4j
public class AdvisorTest {
    @Test
    void advisorTest1() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());
        factory.addAdvisor(advisor);

        ServiceInterface proxy  = (ServiceInterface) factory.getProxy();
        proxy.save();
    }

    @Test
    @DisplayName("직접 만든 Pointcut")
    void advisorTest2() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new MyPointcut(), new TimeAdvice());
        factory.addAdvisor(advisor);

        ServiceInterface proxy  = (ServiceInterface) factory.getProxy();

        proxy.save();
        proxy.find();
    }

    static class MyPointcut implements Pointcut {

        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return new MyMethodMatcher();
        }
    }

    static class MyMethodMatcher implements MethodMatcher {

        private String matchName = "save";;

        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            log.info("포인트컷 호출 method={}, targetClass={}", method.getName(), targetClass);
            boolean result = method.getName().equals(matchName);
            log.info("포인트컷 result={}", result);
            return result;
        }

        @Override
        public boolean isRuntime() {
            return false;
        }

        @Override
        public boolean matches(Method method, Class<?> targetClass, Object... args) {
            throw new UnsupportedOperationException();
        }
    }
}
