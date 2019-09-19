package by.khamenka.patterns.factoryMethod;

/**
 * Фабричный метод (Factory Method) - порождающий шаблон проектирования,
 * определяет стандартный метод создания объекта, не связанный с
 * вызовом конструктора, оставляя решение о том, какой именно объект создавать, за подклассами.
 * <p>
 * Определяет интерфейс для разработки объектов,
 * при этом объекты данного класса могут быть созданы его подклассами.
 * <p>
 * Дилегирует создание объекта подклассам.
 * <p>
 * Цель: отделить создание объектов от их использования.
 */

import by.khamenka.patterns.factoryMethod.Creator.DigitalWatchMaker;
import by.khamenka.patterns.factoryMethod.Creator.RomeWatchMaker;
import by.khamenka.patterns.factoryMethod.Creator.WatchMaker;
import by.khamenka.patterns.factoryMethod.Product.Watch;

public class FactoryMethodApp {

    public static void main(String[] args) {
        /*
         * При добавлении новых сущьностей (новых часов), этот
         * клиентский код не измениться, что является нашей целью целей !
         * */

        // создаём фабрику, в зависимости от ситуации можем поменять одну линию на другую
        WatchMaker watchMaker = getMakerByName("Digital");

        // производим на ней часы - этот метод неизменен, этого мы и пытаемся достигнуть
        Watch watch = watchMaker.createWatсh();
        watch.showTime();

    }

    public static WatchMaker getMakerByName(String makerName) {
        if (makerName.equals("Digital")) {
            return new DigitalWatchMaker();
        } else if (makerName.equals("Rome")) {
            return new RomeWatchMaker();
        } else {
            throw new RuntimeException("Такой линии по производству часов нет: " + makerName);
        }
    }

}
