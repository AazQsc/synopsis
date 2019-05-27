package by.khamenka.patterns.abstractFactory.cars.ordinary;

public class MercedesCar implements OrdinaryCar {
    @Override
    public void move() {
        System.out.println("Mercedes move.");
    }
}
