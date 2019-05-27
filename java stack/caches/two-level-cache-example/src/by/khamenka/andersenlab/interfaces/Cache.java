package by.khamenka.andersenlab.interfaces;

import java.io.IOException;

/**
 * То что реализует этот интерфейс является кешем.
 */
public interface Cache<Key, Value> {
    /**
     * Кешируем объект.
     */
    void cache(Key key, Value value) throws IOException, ClassNotFoundException;

    /**
     * Получить объект по ключу.
     */
    Value getObject(Key key) throws IOException, ClassNotFoundException;

    /**
     * Удалить объект.
     */
    void deleteObject(Key key);

    /**
     * Очистить кеш.
     */
    void clearCache();

    /**
     * Переместить объект, то есть объект будет удалён из кеша и возвращен,
     * Метод используется при рекешировании.
     */
    Value removeObject(Key key) throws IOException, ClassNotFoundException;

    /**
     * Проверяет наличие ключа.
     */
    boolean containsKey(Key key);

    /**
     * Возвращает размер Кеша.
     */
    int size();
}
