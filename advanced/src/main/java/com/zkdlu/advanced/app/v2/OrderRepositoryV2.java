package com.zkdlu.advanced.app.v2;

import com.zkdlu.advanced.trace.TraceId;
import com.zkdlu.advanced.trace.TraceStatus;
import com.zkdlu.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {
    private final HelloTraceV2 trace;


    public void save(final TraceId traceId, final String itemId) {
        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId, "OrderRepository.save()");
            if ("ex".equals(itemId)) {
                throw new IllegalStateException("예외 발생");
            }

            sleep(1000);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    private void sleep(final long mills) {
        try {
            Thread.sleep(mills);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }
}
