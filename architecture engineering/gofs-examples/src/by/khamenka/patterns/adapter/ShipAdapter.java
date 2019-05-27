package by.khamenka.patterns.adapter;

import by.khamenka.patterns.adapter.Submarine.Submarine;
import by.khamenka.patterns.adapter.ship.Ship;

/*
* Будем использовать корабль как подводную лодку :)
* */
public class ShipAdapter implements Submarine{
    private Ship ship;

    public ShipAdapter(Ship ship) {
        this.ship = ship;
    }

    @Override
    public void dive() {
        ship.sink();
    }

    @Override
    public void move() {
        ship.move();
        System.out.println("... but not so fast");
    }
}
