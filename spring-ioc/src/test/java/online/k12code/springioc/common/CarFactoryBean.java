package online.k12code.springioc.common;

import online.k12code.springioc.bean.Car;
import online.k12code.springioc.factory.FactoryBean;

/**
 * @author Carl
 * @since 1.0.0
 */
public class CarFactoryBean implements FactoryBean<Car> {

    private String brand;

    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        car.setBrand(brand);
        return car;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
