package by.khamenka.patterns.factoryMethod.Product;

import java.util.Date;

public class RomeWatch implements Watch {
    @Override
    public void showTime() {
        System.out.println(new Date() + "In fact, you are in Rome");
    }
}
