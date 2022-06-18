package com.zkdlu.advanced.trace.logtrace;

import org.junit.jupiter.api.Test;

class FieldLogTraceTest {
    LogTrace trace = new FieldLogTrace();

    @Test
    void begin_end_level2() {
        final var status1 = trace.begin("hello1");
        final var status2 = trace.begin("hello2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception_level2() {
        final var status1 = trace.begin("hello1");
        final var status2 = trace.begin("hello2");
        trace.exception(status2, new IllegalStateException());
        trace.end(status1);
    }
}