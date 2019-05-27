package by.khamenka.patterns.strategy.strategyClient;

import by.khamenka.patterns.strategy.strategy.LearningStrategy;

public class Student {
    LearningStrategy strategy;

    public void setStrategy(LearningStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(){
        strategy.learn();
    }
}
