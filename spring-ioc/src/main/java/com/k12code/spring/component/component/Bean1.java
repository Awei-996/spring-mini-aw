package com.k12code.spring.component.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Carl
 * @since 1.0.0
 */
public class Bean1 {
    private static final Logger log = LoggerFactory.getLogger(BeanCom.class);

    public Bean1() {
        log.info("构造方法: Bean1");
    }
}
