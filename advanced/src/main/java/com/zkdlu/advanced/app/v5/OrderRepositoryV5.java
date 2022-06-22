package com.zkdlu.advanced.app.v5;

import com.zkdlu.advanced.trace.callback.TraceTemplate;
import com.zkdlu.advanced.trace.logtrace.LogTrace;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {

    private final TraceTemplate template;

    public OrderRepositoryV5(final LogTrace trace) {
        this.template = new TraceTemplate(trace);
    }

    public void save(final String itemId) {
        template.execute("OrderRepository.save()", () -> {
            if ("ex".equals(itemId)) {
                throw new IllegalStateException("예외 발생");
            }

            sleep(1000);
            return null;
        });
    }

    private void sleep(final long mills) {
        try {
            Thread.sleep(mills);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }
}
