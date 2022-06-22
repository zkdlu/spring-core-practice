package com.zkdlu.proxy.trace.logtrace;

import com.zkdlu.proxy.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);
}
