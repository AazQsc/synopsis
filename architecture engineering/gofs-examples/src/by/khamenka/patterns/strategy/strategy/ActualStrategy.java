package by.khamenka.patterns.strategy.strategy;

public class ActualStrategy implements LearningStrategy{
    @Override
    public void learn() {
        System.out.println("Обучение в зоне актуального развития.");
    }
}
