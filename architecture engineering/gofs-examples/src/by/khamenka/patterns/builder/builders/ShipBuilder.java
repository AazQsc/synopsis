package by.khamenka.patterns.builder.builders;

import by.khamenka.patterns.builder.complicatedObject.Ship;

abstract public class ShipBuilder {
    protected Ship ship;

    public void createShip(){
        this.ship = new Ship();
    }

    abstract public void buildType();
    abstract public void buildSpeed();
    abstract public void buildGuns();

    public Ship getShip(){
        return ship;
    }
}
