package com.k12code.spring.component.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Carl
 * @since 1.0.0
 */
@Component
public class BeanCom {
    private static final Logger log = LoggerFactory.getLogger(BeanCom.class);

    public BeanCom() {
        log.info("构造方法: beanCom");
    }
}
