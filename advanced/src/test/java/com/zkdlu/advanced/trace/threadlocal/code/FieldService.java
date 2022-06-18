package com.zkdlu.advanced.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldService {
    private String nameStore;

    public String logic(String name) {
        log.info("name={} -> nameStore={}", name, nameStore);
        nameStore = name;
        sleep(1000);
        log.info("nameStore={}", nameStore);
        return nameStore;
    }

    private void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
