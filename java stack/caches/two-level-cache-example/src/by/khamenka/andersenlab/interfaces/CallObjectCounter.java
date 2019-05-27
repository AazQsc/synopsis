package by.khamenka.andersenlab.interfaces;

import java.util.Set;

/**
 * Интерфейс используется алгоритмами вытеснения,
 * для подсчёта количества (частоты) вызовов объекта в кеше.
 */
public interface CallObjectCounter<Key> {
    /**
     * Возвращает set ключей, сортированый в соответствии с количеством
     * вызовов каждого ключа.
     */
    Set<Key> getMostCalledKeys();

    /**
     * Возвращает количество вызовов объекта по ключу
     */
    int getNumberOfCallsToObject(Key key);
}
