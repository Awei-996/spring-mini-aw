package com.k12code.spring.scope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author Carl
 * @since 1.0.0
 */
@Component
public class F1 {

    @Autowired
    private S1 s1;

    @Autowired
    private S2 s2;

    public S1 getS1() {
        return s1;
    }

    public S2 getS2() {
        return s2;
    }
}
