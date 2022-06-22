package com.zkdlu.advanced.trace.strategy;

import com.zkdlu.advanced.trace.strategy.code.template.Callback;
import com.zkdlu.advanced.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Spring에서 xxxTemplate은 Template Callback 패턴임. 람다를 이용한 Strategy 패턴
 */
@Slf4j
public class TemplateCallbackTest {
    @Test
    void callbackV1() {
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("logic1 실행");
            }
        });

        TimeLogTemplate template2 = new TimeLogTemplate();
        template2.execute(new Callback() {
            @Override
            public void call() {
                log.info("logic2 실행");
            }
        });
    }
}
