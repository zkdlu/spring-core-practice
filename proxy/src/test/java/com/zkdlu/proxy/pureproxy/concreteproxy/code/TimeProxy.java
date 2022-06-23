package com.zkdlu.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreteLogic {

    private ConcreteLogic concreteLogic;

    public TimeProxy(final ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    @Override
    public String operation() {
        long start = System.currentTimeMillis();
        String result = concreteLogic.operation();
        long end = System.currentTimeMillis();

        log.info("TimeProxy resultTime = {}ms", end - start);
        return result;
    }
}
