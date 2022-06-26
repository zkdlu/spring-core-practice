package com.zkdlu.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {
    @Test
    void reflection0() {
        final Hello target = new Hello();

        log.info("start");
        final String result1 = target.callA();
        log.info("result={}", result1);

        log.info("start");
        final String result2 = target.callB();
        log.info("result={}", result2);
    }

    @Test
    void reflection1() throws Exception {
        final Class classHello = Class.forName("com.zkdlu.proxy.jdkdynamic.ReflectionTest$Hello");
        final Hello target = new Hello();

        log.info("start");
        final Method methodCallA = classHello.getMethod("callA");
        final Object result1 = methodCallA.invoke(target);
        log.info("result={}", result1);

        log.info("start");
        final Method methodCallB = classHello.getMethod("callB");
        final Object result2 = methodCallB.invoke(target);
        log.info("result={}", result2);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("call A");
            return "A";
        }

        public String callB() {
            log.info("call B");
            return "B";
        }
    }
}
