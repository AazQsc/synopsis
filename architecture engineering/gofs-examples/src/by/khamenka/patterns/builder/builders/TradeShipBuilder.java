package by.khamenka.patterns.builder.builders;

import by.khamenka.patterns.builder.complicatedObject.ShipType;

public class TradeShipBuilder extends ShipBuilder{
    @Override
    public void buildType() {
        ship.setType(ShipType.TRADE);
    }

    @Override
    public void buildSpeed() {
        ship.setSpeed(21);
    }

    @Override
    public void buildGuns() {
        ship.setGuns(10 );
    }
}
