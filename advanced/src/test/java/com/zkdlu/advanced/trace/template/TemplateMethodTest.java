package com.zkdlu.advanced.trace.template;

import com.zkdlu.advanced.trace.template.code.AbstractTemplate;
import com.zkdlu.advanced.trace.template.code.SubClassLogic1;
import com.zkdlu.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {
    @Test
    void templateMethodV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();

        log.info("logic1 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("resultTime={}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();

        log.info("logic2 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("resultTime={}", resultTime);
    }

    @Test
    void templateMethodV1() {
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();
    }

    @Test
    void templateMethodV2() {
        new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("logic1 실행");
            }
        }.execute();

        new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("logic2 실행");
            }
        }.execute();
    }
}
