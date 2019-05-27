package by.khamenka.patterns.abstractFactory.cars.ordinary;

public class RenoCar implements OrdinaryCar{
    @Override
    public void move() {
        System.out.println("Reno move.");
    }
}
