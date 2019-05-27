package by.khamenka.patterns.abstractFactory.cars.ordinary;

public class FerrariCar implements OrdinaryCar {
    @Override
    public void move() {
        System.out.println("Ferrari move.");
    }
}
