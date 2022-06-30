package com.zkdlu.proxy.advisor;

import com.zkdlu.proxy.common.advice.TimeAdvice;
import com.zkdlu.proxy.common.service.ServiceImpl;
import com.zkdlu.proxy.common.service.ServiceInterface;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class AdvisorTest {
    @Test
    void advisorTest1() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());
        factory.addAdvisor(advisor);

        ServiceInterface proxy  = (ServiceInterface) factory.getProxy();
        proxy.save();
    }
}
