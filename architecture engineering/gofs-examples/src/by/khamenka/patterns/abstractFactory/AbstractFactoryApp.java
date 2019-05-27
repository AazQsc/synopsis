package by.khamenka.patterns.abstractFactory;

import by.khamenka.patterns.abstractFactory.cars.F1.Bolide;
import by.khamenka.patterns.abstractFactory.cars.ordinary.OrdinaryCar;
import by.khamenka.patterns.abstractFactory.factorys.Company;
import by.khamenka.patterns.abstractFactory.factorys.interfaces.CarsFactory;
import by.khamenka.patterns.abstractFactory.factorys.realFactorys.FerrariFactory;
import by.khamenka.patterns.abstractFactory.factorys.realFactorys.MercedesFactory;
import by.khamenka.patterns.abstractFactory.factorys.realFactorys.RenoFactory;

/**
 * Абстрактная фабрика - пораждающий шаблон проектирования, цель которой предоставить
 * интерфейс для создания множества связанных между собой или независимых объектов,
 * конкретные классы которых неизвестны.
 * <p>
 * Иначе говоря: предоставляет интерфейс для создания групп связанных или независимых
 * объектов, не указывая их конкретный класс
 * */
public class AbstractFactoryApp {

    public static void main(String[] args) {
        // Будут производиться машины, соответствующие марке выбранной компании.
        CarsFactory carsFactory = getFactoryByCompany(Company.FERRARI);

        OrdinaryCar car = carsFactory.createOrdinaryCar();
        Bolide bolide = carsFactory.createBolide();

        car.move();
        bolide.moveRapid();
    }

    private static CarsFactory getFactoryByCompany(Company companyName){
        switch (companyName){
            case FERRARI:
                return new FerrariFactory();
            case MERCEDES:
                return new MercedesFactory();
            case RENO:
                return new RenoFactory();
            default:
                throw new RuntimeException("Unsupported company: " + companyName);
        }
    }
}
