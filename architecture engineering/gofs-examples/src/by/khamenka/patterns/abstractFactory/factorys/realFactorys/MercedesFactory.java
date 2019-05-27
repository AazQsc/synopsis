package by.khamenka.patterns.abstractFactory.factorys.realFactorys;

import by.khamenka.patterns.abstractFactory.cars.F1.Bolide;
import by.khamenka.patterns.abstractFactory.cars.F1.MercedesBolide;
import by.khamenka.patterns.abstractFactory.cars.ordinary.MercedesCar;
import by.khamenka.patterns.abstractFactory.cars.ordinary.OrdinaryCar;
import by.khamenka.patterns.abstractFactory.factorys.interfaces.CarsFactory;

public class MercedesFactory implements CarsFactory {
    @Override
    public Bolide createBolide() {
        return new MercedesBolide();
    }

    @Override
    public OrdinaryCar createOrdinaryCar() {
        return new MercedesCar();
    }
}
