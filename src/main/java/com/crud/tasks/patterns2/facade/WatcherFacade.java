package com.crud.tasks.patterns2.facade;

import com.crud.tasks.patterns2.aop.calculator.Watcher;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WatcherFacade {
    private static final Logger LOGGER =
        LoggerFactory.getLogger(WatcherFacade.class);
    @Before("execution(* com.crud.tasks.patterns2.facade.api.OrderFacade.processOrder(..))")
    public void logEvent() {
        LOGGER.info("Application was triggered");
    }

}
