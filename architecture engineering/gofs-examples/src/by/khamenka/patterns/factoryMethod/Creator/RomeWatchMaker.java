package by.khamenka.patterns.factoryMethod.Creator;

import by.khamenka.patterns.factoryMethod.Product.RomeWatch;
import by.khamenka.patterns.factoryMethod.Product.Watch;

/*
* Это линия по производству римских часов на фабрике
* */
public class RomeWatchMaker implements WatchMaker {
    @Override
    public Watch createWatсh() {
        return new RomeWatch();
    }
}
