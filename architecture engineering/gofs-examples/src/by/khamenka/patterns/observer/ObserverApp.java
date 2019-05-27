package by.khamenka.patterns.observer;

import by.khamenka.patterns.observer.publisher.Book;
import by.khamenka.patterns.observer.publisher.BookWriter;
import by.khamenka.patterns.observer.subscriber.BookReader;

/**
 * Наблюдатель - поведенческий шаблон проектирования, cпецифицирует зависимость типа
 * "один ко многим" между различными объектами, так что при изменении состояния одного объекта
 * все зависящие от него получают извещение и автоматически обновляются.
 *
 * Круто если подписчик имеет возможность отписаться.
 */

// что-то такое уже видел.. самые разные eventHandler'ы
// думаю очень полезная вещь в многопоточной среде
public class ObserverApp {
    public static void main(String[] args) {
        BookWriter writer = new BookWriter();
        writer.addObserver(new BookReader());

        writer.setLastBook(new Book("Паттерны проектирования"));

    }
}
