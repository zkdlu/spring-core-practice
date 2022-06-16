package com.zkdlu.advanced.app.v2;

import com.zkdlu.advanced.trace.TraceId;
import com.zkdlu.advanced.trace.TraceStatus;
import com.zkdlu.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 trace;

    public void orderItem(final TraceId traceId, final String itemId) {
        TraceStatus status = null;

        try {
            status = trace.beginSync(traceId,"OrderService.orderItem()");
            orderRepository.save(status.getTraceId(), itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
