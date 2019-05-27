package by.khamenka.patterns.abstractFactory.factorys.realFactorys;

import by.khamenka.patterns.abstractFactory.cars.F1.Bolide;
import by.khamenka.patterns.abstractFactory.cars.F1.FerrariBolide;
import by.khamenka.patterns.abstractFactory.cars.ordinary.FerrariCar;
import by.khamenka.patterns.abstractFactory.cars.ordinary.OrdinaryCar;
import by.khamenka.patterns.abstractFactory.factorys.interfaces.CarsFactory;

public class FerrariFactory implements CarsFactory {
    @Override
    public Bolide createBolide() {
        return new FerrariBolide();
    }

    @Override
    public OrdinaryCar createOrdinaryCar() {
        return new FerrariCar();
    }
}
