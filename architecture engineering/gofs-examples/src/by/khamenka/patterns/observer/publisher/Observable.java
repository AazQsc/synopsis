package by.khamenka.patterns.observer.publisher;

import by.khamenka.patterns.observer.subscriber.Observer;

// Издатель
public interface Observable {
    void addObserver(Observer obs);
    void removeObserver(Observer obs);
    void notifyObservers();
}
