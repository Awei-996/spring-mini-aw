package com.k12code.spring.component.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @author Carl
 * @since 1.0.0
 */
@Controller
public class Bean2 {
    private static final Logger log = LoggerFactory.getLogger(BeanCom.class);

    public Bean2() {
        log.info("构造方法: Bean1");
    }
}
