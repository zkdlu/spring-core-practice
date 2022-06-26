package com.zkdlu.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class TimeInvocationHandler implements InvocationHandler {

    private final Object target;

    public TimeInvocationHandler(final Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("time proxy 실행");
        long start = System.currentTimeMillis();

        Object result = method.invoke(target, args);

        long end = System.currentTimeMillis();
        log.info("resultTime = {}ms", end - start);
        return result;
    }
}
