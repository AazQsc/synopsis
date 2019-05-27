package by.khamenka.patterns.adapter.ship;

public class SomeShip implements Ship{
    @Override
    public void move() {
        System.out.println("The ship is moved");
    }

    @Override
    public void sink() {
        System.out.println("The ship is sinking !!!");
    }
}
