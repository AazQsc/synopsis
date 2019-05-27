package by.khamenka.patterns.factoryMethod.Product;

import java.util.Date;

public class DigitalWatch implements Watch {
    @Override
    public void showTime() {
        System.out.println(new Date());
    }
}
