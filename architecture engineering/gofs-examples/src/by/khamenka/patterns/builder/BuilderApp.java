package by.khamenka.patterns.builder;

import by.khamenka.patterns.builder.builders.TradeShipBuilder;
import by.khamenka.patterns.builder.complicatedObject.Ship;

/**
 * Builder - пораждающий шаблон проектирования, который позволяет отделить
 * создание сложного объекта от его представления, позволяя использовать один и тот же
 * процесс разработки для создания различных представлений.
 *
 * Ещё есть вариант сделать билдер статическим внутренним классом в классе, который он создаёт.
 * И управлять набором параметров.
 *
 * Маячек для билдера - конструктор с большим количеством параметров.
 */
public class BuilderApp {


    public static void main(String[] args) {
        Director director = new Director();

        // Если назначить другого строителя будут строиться другие корабли
        director.setBuilder(new TradeShipBuilder());
        Ship ship = director.buildShip();
        System.out.println(ship);
    }

}