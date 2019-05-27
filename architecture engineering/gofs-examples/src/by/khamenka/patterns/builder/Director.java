package by.khamenka.patterns.builder;

import by.khamenka.patterns.builder.builders.ShipBuilder;
import by.khamenka.patterns.builder.complicatedObject.Ship;

public class Director {
    private ShipBuilder builder;

    public void setBuilder(ShipBuilder builder){
        this.builder = builder;
    }

    public Ship buildShip(){
        builder.createShip();

        builder.buildType();
        builder.buildSpeed();
        builder.buildGuns();

        Ship ship = builder.getShip();
        return ship;
    }
}
