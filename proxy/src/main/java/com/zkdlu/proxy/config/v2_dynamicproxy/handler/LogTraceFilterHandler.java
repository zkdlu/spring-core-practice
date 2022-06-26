package com.zkdlu.proxy.config.v2_dynamicproxy.handler;

import com.zkdlu.proxy.trace.TraceStatus;
import com.zkdlu.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class LogTraceFilterHandler implements InvocationHandler {
    private final Object target;
    private final LogTrace trace;
    private final String[] patterns;

    public LogTraceFilterHandler(final Object target, final LogTrace trace, final String[] patterns) {
        this.target = target;
        this.trace = trace;
        this.patterns = patterns;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String methodName = method.getName();
        if (!PatternMatchUtils.simpleMatch(patterns, methodName)) {
            return method.invoke(target, args);
        }

        TraceStatus status = null;
        try {
            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = trace.begin(message);

            Object result = method.invoke(target, args);
            trace.end(status);
            return result;
        } catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }
}
