package by.khamenka.patterns.builder.builders;

import by.khamenka.patterns.builder.complicatedObject.ShipType;

public class FrigateShipBuilder extends ShipBuilder{
    @Override
    public void buildType() {
        ship.setType(ShipType.FRIGATE);
    }

    @Override
    public void buildSpeed() {
        ship.setSpeed(17);
    }

    @Override
    public void buildGuns() {
        ship.setGuns(50);
    }
}
