package com.zkdlu.advanced.trace.strategy;

import com.zkdlu.advanced.trace.strategy.code.strategy.ContextV2;
import com.zkdlu.advanced.trace.strategy.code.strategy.StrategyLogic1;
import com.zkdlu.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    @Test
    void strategyV1() {
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    @Test
    void strategyV2() {
        ContextV2 context = new ContextV2();
        context.execute(() -> log.info("logic1 실행"));
        context.execute(() -> log.info("logic2 실행"));
    }
}
