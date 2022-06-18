package com.zkdlu.advanced;

import com.zkdlu.advanced.trace.logtrace.FieldLogTrace;
import com.zkdlu.advanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {
    @Bean
    public LogTrace logTrace() {
        return new FieldLogTrace();
    }
}
