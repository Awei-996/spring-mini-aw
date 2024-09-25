package com.k12code.spring.jdkProxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Carl
 * @since 1.0.0
 */
public class Impl implements Inter{

    private static final Logger log = LoggerFactory.getLogger(Impl.class);

    @Override
    public void foo() {
        log.info("=============foo");
    }
}
