package by.khamenka.patterns.facade;

import by.khamenka.patterns.facade.facade.Computer;

/**
 * Фасад - структурный шаблон проектирования, позволяющий скрыть сложность системы
 * путем сведения всех возможных внешних вызовов к одному объекту, делегирующему их соответствующим
 * объектам системы.
 * <p>
 * Предоставляет единый интерфейс к множеству операций или
 * интерфейсов в системе на основе унифицированного интерфейса для облегчения работы с системой.
 * <p>
 * Скрываем сложную систему за фасадом:
 * Классы Power, DVDRom и HDD - абстракция сложной системы.
 */
public class FacadeApp {
    public static void main(String[] args) {

        /*
        Это то, чего мы хотим избежать:

        Power power = new Power();
        power.on();

        DVDRom dvd = new DVDRom();
        dvd.loadData();

        HDD hdd = new HDD();
        hdd.copyFromDVD(dvd);
        */

        Computer computer = new Computer();
        computer.copy();

    }
}
