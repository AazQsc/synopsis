package by.khamenka.patterns.strategy;

import by.khamenka.patterns.strategy.strategy.ActualStrategy;
import by.khamenka.patterns.strategy.strategy.ProximalStrategy;
import by.khamenka.patterns.strategy.strategyClient.Student;

/**
 * Стратегия - 	поведенческий шаблон проектирования в котором мы определяем множество алгоритмов,
 * инкапсулируя их все и позволяя подставлять один вместо другого.
 * При этом можно изменять алгоритм независимо от клиента, который им пользуется.
 */
public class StrategyApp {

    public static void main(String[] args) {
        Student student = new Student();

        student.setStrategy(new ActualStrategy());
        student.executeStrategy();

        student.setStrategy(new ProximalStrategy());
        student.executeStrategy();
    }
}
