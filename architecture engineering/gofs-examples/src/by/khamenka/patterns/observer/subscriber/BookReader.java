package by.khamenka.patterns.observer.subscriber;

import by.khamenka.patterns.observer.publisher.Book;

public class BookReader implements Observer {
    @Override
    public void handleEvent(Book book) {
        System.out.println("Я читаю '" + book.getName()+"'.");
    }
}
