package by.khamenka.patterns.adapter;

import by.khamenka.patterns.adapter.Submarine.SomeSubmarine;
import by.khamenka.patterns.adapter.Submarine.Submarine;
import by.khamenka.patterns.adapter.ship.SomeShip;

/**
 * Aдаптер - структурный шаблон проектирования, цель которого:
 * преобразование интерфейса к другому интерфейсу, на который расчитан клиент.
 * <p>
 * Цель: обеспечить совместную работу классов, невозможную в обычных условиях, из-за
 * несовместимости интерфейсов.
 */
public class AdapterApp {

    public static void main(String[] args) {
        SomeSubmarine u232 = new SomeSubmarine();
        test(u232);
        SomeShip titanik = new SomeShip();
        /*
        * Это работать, разумееется, не будет:
        * test(titanik);
        * */

        System.out.println("-----------");

        /*
        * А вот так будет!
        * */
        Submarine u676 = new ShipAdapter(titanik);
        test(u676);
    }

    public static void test(Submarine submarine){
        submarine.move();
        submarine.dive();
    }

}

