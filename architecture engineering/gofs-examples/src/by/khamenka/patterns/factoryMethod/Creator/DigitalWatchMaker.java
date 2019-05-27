package by.khamenka.patterns.factoryMethod.Creator;

import by.khamenka.patterns.factoryMethod.Product.DigitalWatch;
import by.khamenka.patterns.factoryMethod.Product.Watch;

/*
 * Это линия по производству электронных часов на фабрике
 * */
public class DigitalWatchMaker implements WatchMaker {

    @Override
    public Watch createWatсh() {
        return new DigitalWatch();
    }
}
