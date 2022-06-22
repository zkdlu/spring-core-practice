package com.zkdlu.proxy.pureproxy.proxy;

import com.zkdlu.proxy.pureproxy.proxy.code.CacheProxy;
import com.zkdlu.proxy.pureproxy.proxy.code.ProxyPatternClient;
import com.zkdlu.proxy.pureproxy.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {
    @Test
    void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    void cacheProxyTest() {
        ProxyPatternClient client = new ProxyPatternClient(new CacheProxy(new RealSubject()));
        client.execute();
        client.execute();
        client.execute();
    }
}
