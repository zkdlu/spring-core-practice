package com.zkdlu.proxy.config.v2_proxy.concrete_proxy;

import com.zkdlu.proxy.app.v2.OrderServiceV2;
import com.zkdlu.proxy.trace.TraceStatus;
import com.zkdlu.proxy.trace.logtrace.LogTrace;

public class OrderServiceConcreteProxy extends OrderServiceV2 {
    private final OrderServiceV2 target;
    private final LogTrace trace;

    public OrderServiceConcreteProxy(final OrderServiceV2 target, final LogTrace trace) {
        super(null);
        this.target = target;
        this.trace = trace;
    }

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");
            target.orderItem(itemId);
            trace.end(status);

        } catch (Exception e){
            trace.exception(status, e);
        }
    }
}
