package online.k12code.springioc.bean;

import online.k12code.springioc.factory.DisposableBean;
import online.k12code.springioc.factory.InitializingBean;

/**
 * @author Carl
 * @since 1.0.0
 */
public class Person2 implements InitializingBean, DisposableBean {

    private String name;

    private int age;

    private Car car;

    public void customInitMethod() {
        System.err.println("I was born in the method named customInitMethod");
    }

    public void customDestroyMethod() {
        System.err.println("I died in the method named customDestroyMethod");
    }

    @Override
    public void destroy() throws Exception {
        System.err.println("destroy()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.err.println("afterPropertiesSet()");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
