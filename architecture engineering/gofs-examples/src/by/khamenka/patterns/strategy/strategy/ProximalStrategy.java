package by.khamenka.patterns.strategy.strategy;

public class ProximalStrategy implements LearningStrategy{
    @Override
    public void learn() {
        System.out.println("Обучение в зоне ближайшего развития.");
    }
}
