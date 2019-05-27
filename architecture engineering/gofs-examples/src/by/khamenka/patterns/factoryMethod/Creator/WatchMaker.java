package by.khamenka.patterns.factoryMethod.Creator;

import by.khamenka.patterns.factoryMethod.Product.Watch;

/*
* производитель часов
* это абстракция фабрики
*
* это может быть абстрактный класс
* */
public interface WatchMaker {
    // это и есть наш фабричный метод
    Watch createWatсh();
}
