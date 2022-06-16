package com.zkdlu.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {
    public void save(final String itemId) {
        if ("ex".equals(itemId)) {
            throw new IllegalStateException("예외 발생");
        }

        sleep(1000);
    }

    private void sleep(final long mills) {
        try {
            Thread.sleep(mills);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }
}
