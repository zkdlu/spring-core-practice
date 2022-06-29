package com.zkdlu.proxy.cglib.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {
    private final Object target;

    public TimeMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("time proxy 실행");
        long start = System.currentTimeMillis();

        // 성능상 method를 사용하는것보다 권장
        Object result = methodProxy.invoke(target, args);

        long end = System.currentTimeMillis();
        log.info("resultTime = {}ms", end - start);
        return result;
    }
}
