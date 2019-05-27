package by.khamenka.patterns.observer.publisher;

import by.khamenka.patterns.observer.subscriber.Observer;

import java.util.ArrayList;
import java.util.List;

public class BookWriter implements Observable{
    private Book lastBook;

    List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        observers.remove(obs);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.handleEvent(lastBook);
        }
    }

    public Book getLastBook() {
        return lastBook;
    }

    // Как только произошло изменение, оповещаем подписчиков.
    public void setLastBook(Book lastBook) {
        this.lastBook = lastBook;
        notifyObservers();
    }
}
