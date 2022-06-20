package com.zkdlu.advanced.app.v4;

import com.zkdlu.advanced.trace.logtrace.LogTrace;
import com.zkdlu.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {
    private final OrderRepositoryV4 orderRepository;
    private final LogTrace trace;

    public void orderItem(final String itemId) {
        final AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            public Void call() {
                orderRepository.save(itemId);
                return null;
            }
        };

        template.execute("OrderService.orderItem()");
    }
}
