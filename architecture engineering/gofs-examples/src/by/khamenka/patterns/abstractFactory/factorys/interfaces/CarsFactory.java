package by.khamenka.patterns.abstractFactory.factorys.interfaces;

import by.khamenka.patterns.abstractFactory.cars.F1.Bolide;
import by.khamenka.patterns.abstractFactory.cars.ordinary.OrdinaryCar;

/*
 * Это и есть наша абстрактная фабрика.
 */
public interface CarsFactory {
    Bolide createBolide();
    OrdinaryCar createOrdinaryCar();
}
