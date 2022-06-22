package com.zkdlu.proxy.pureproxy.decorator;

import com.zkdlu.proxy.pureproxy.decorator.code.Component;
import com.zkdlu.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import com.zkdlu.proxy.pureproxy.decorator.code.MessageDecorator;
import com.zkdlu.proxy.pureproxy.decorator.code.RealComponent;
import com.zkdlu.proxy.pureproxy.decorator.code.TimeDecorator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {

    @Test
    void noDecorator() {
        Component component = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(component);
        client.execute();
    }

    @Test
    void decorator1() {
        Component component = new RealComponent();
        Component messageDecorator = new MessageDecorator(component);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);
        client.execute();
    }

    @Test
    void decorator2() {
        Component component = new RealComponent();
        Component messageDecorator = new MessageDecorator(component);
        Component timeDecorator = new TimeDecorator(messageDecorator);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);
        client.execute();
    }
}
