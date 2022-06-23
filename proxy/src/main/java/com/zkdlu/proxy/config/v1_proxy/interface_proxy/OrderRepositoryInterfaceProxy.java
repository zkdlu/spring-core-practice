package com.zkdlu.proxy.config.v1_proxy.interface_proxy;

import com.zkdlu.proxy.app.v1.OrderRepositoryV1;
import com.zkdlu.proxy.trace.TraceStatus;
import com.zkdlu.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderRepositoryV1 {

    private final OrderRepositoryV1 target;
    private final LogTrace trace;

    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepository.save()");
            target.save(itemId);
            trace.end(status);

        } catch (Exception e){
            trace.exception(status, e);
        }
    }
}
