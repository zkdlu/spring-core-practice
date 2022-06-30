package com.zkdlu.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/*
Pointcut: 부가기능을 어디에 적용할지 판단하는 필터링 로직
Advice : 프록시가 호출하는 부가기능
Advisor: Pointcut + Advice
 */
@Slf4j
public class TimeAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("time proxy 실행");
        long start = System.currentTimeMillis();

        Object result = invocation.proceed();

        long end = System.currentTimeMillis();
        log.info("resultTime = {}ms", end - start);
        return result;
    }
}
