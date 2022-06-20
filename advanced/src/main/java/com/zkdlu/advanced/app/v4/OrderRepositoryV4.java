package com.zkdlu.advanced.app.v4;

import com.zkdlu.advanced.trace.logtrace.LogTrace;
import com.zkdlu.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {
    private final LogTrace trace;


    public void save(final String itemId) {
        final AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            public Void call() {
                if ("ex".equals(itemId)) {
                    throw new IllegalStateException("예외 발생");
                }

                sleep(1000);
                return null;
            }
        };

        template.execute("OrderRepository.save()");
    }

    private void sleep(final long mills) {
        try {
            Thread.sleep(mills);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }
}
