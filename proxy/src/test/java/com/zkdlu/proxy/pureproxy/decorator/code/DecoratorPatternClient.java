package com.zkdlu.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecoratorPatternClient {

    private Component component;

    public DecoratorPatternClient(final Component component) {
        this.component = component;
    }

    public void execute() {
        component.operation();
    }
}
