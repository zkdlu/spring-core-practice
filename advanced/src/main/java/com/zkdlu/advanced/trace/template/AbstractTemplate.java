package com.zkdlu.advanced.trace.template;

import com.zkdlu.advanced.trace.TraceStatus;
import com.zkdlu.advanced.trace.logtrace.LogTrace;

public abstract class AbstractTemplate<T> {
    private final LogTrace trace;

    public AbstractTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public T execute(String mesage) {
        TraceStatus status = null;

        try {
            status = trace.begin(mesage);

            T result = call();

            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    public abstract T call();
}
