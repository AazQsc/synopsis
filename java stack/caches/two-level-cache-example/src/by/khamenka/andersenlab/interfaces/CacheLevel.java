package by.khamenka.andersenlab.interfaces;

import java.io.IOException;

/**
 * Интерфейс является абстракцией двухуровневого кеша.
 * Метод recache() используется для перехода объекта с одного уровня кеша к другому.
 * */
public interface CacheLevel<Key, Value>
        extends Cache<Key, Value>, CallObjectCounter<Key>{

    void recache() throws IOException, ClassNotFoundException;
}
