package com.zkdlu.proxy.pureproxy.concreteproxy;

import com.zkdlu.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import com.zkdlu.proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import com.zkdlu.proxy.pureproxy.concreteproxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {

    @Test
    void noProxy() {
        ConcreteLogic logic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(logic);
        client.execute();
    }

    @Test
    void addProxy() {
        ConcreteLogic logic = new ConcreteLogic();
        TimeProxy timeProxy = new TimeProxy(logic);
        ConcreteClient client = new ConcreteClient(timeProxy);
        client.execute();
    }
}
