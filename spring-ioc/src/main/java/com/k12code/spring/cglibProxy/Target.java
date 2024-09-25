package com.k12code.spring.cglibProxy;

/**
 * @author Carl
 * @since 1.0.0
 */
public class Target {

    public void save1(){
        System.out.println("save1……");
    }

    public void save2(int i){
        System.err.println("save2……"+i);
    }

    public void save3(long j) {
        System.err.println("save3……"+j);
    }

}
