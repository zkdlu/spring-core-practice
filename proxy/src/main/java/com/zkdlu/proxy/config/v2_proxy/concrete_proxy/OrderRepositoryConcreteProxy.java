package com.zkdlu.proxy.config.v2_proxy.concrete_proxy;

import com.zkdlu.proxy.app.v2.OrderRepositoryV2;
import com.zkdlu.proxy.trace.TraceStatus;
import com.zkdlu.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryConcreteProxy extends OrderRepositoryV2 {

    private final OrderRepositoryV2 target;
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
