package com.zkdlu.advanced.trace.logtrace;

import com.zkdlu.advanced.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);
}
