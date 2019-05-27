package by.khamenka.patterns.observer.subscriber;

import by.khamenka.patterns.observer.publisher.Book;

// Подписчик
public interface Observer {
    void handleEvent(Book book);
}
