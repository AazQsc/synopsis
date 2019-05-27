package by.khamenka.patterns.delegate;

import by.khamenka.patterns.delegate.delegate.Painter;
import by.khamenka.patterns.delegate.figures.Circle;
import by.khamenka.patterns.delegate.figures.Square;
import by.khamenka.patterns.delegate.figures.Triangle;

/**
 * Делегат -  структурный шаблон проектирования, в котором
 * объект внешне выражает некоторое поведение,
 * но в реальности передаёт ответственность за выполнение этого поведения связанному объекту.
 */

public class DelegateApp {

    public static void main(String[] args) {
        Painter painter = new Painter();

        /*
         * Класс чьим делегатом являемся painter
         * определяте работу метода
         *
         * отличный пример полиморфизма к слову
         */

        painter.setGraphics(new Circle());
        painter.draw();

        painter.setGraphics(new Triangle());
        painter.draw();

        painter.setGraphics(new Square());
        painter.draw();
    }
}
