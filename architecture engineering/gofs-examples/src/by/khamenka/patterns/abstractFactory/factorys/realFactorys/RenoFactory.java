package by.khamenka.patterns.abstractFactory.factorys.realFactorys;

import by.khamenka.patterns.abstractFactory.cars.F1.Bolide;
import by.khamenka.patterns.abstractFactory.cars.F1.RenoBolibe;
import by.khamenka.patterns.abstractFactory.cars.ordinary.OrdinaryCar;
import by.khamenka.patterns.abstractFactory.cars.ordinary.RenoCar;
import by.khamenka.patterns.abstractFactory.factorys.interfaces.CarsFactory;

public class RenoFactory implements CarsFactory{
    @Override
    public Bolide createBolide() {
        return new RenoBolibe();
    }

    @Override
    public OrdinaryCar createOrdinaryCar() {
        return new RenoCar();
    }
}
