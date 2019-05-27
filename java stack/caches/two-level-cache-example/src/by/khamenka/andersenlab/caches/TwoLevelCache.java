package by.khamenka.andersenlab.caches;

import by.khamenka.andersenlab.interfaces.CacheLevel;

import java.io.IOException;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * Собственно это наш двухуровневый кеш.
 * Он инкапсулирует в себе два уровня кеша, и устанавливает
 * некоторую политику взаимоотношений между ними:
 * 1. Политика инвалидации, - алгоритм вытеснения на основе самых редко вызываемых объектов.
 * 2. Ограничение кол-ва памяти (maxRamCacheCapacity, сейчас переменная не используеться
 * но возможно использовать это ограничение аналогично numberOfRequestsForRecahce,
 * то есть сейчас рекеширование производится при увеличении количества запросов к объекту,
 * можно же производить рекеширование в тот момент, когда количество объектов в memoryCache
 * достигнет определённой отметки; ограничений на MemoryCache нет).
 * 3. Конкурентный доступ к кешу.
 */
public class TwoLevelCache<Key, Value extends Serializable> implements CacheLevel<Key, Value> {
    RamCache<Key, Value> ramCache;
    MemoryCache<Key, Value> memoryCache;

    /**
     * Максимальное количество записей в кэше оперативной памяти.
     * Если количество элементов превосходит это число - вызывается алгоритм рекэширования.
     */
    private int maxRamCacheCapacity;
    /**
     * Количество запросов к кэшу после последнего рекэширования.
     */
    private int numberOfRequests;
    /**
     * Количество запросов, необходимое для рекэширования.
     */
    private int requestsForRecahce;

    public TwoLevelCache(int maxRamCacheCapacity, int requestsForRecache) {
        this.maxRamCacheCapacity = maxRamCacheCapacity;
        this.requestsForRecahce = requestsForRecache;
        this.ramCache = new RamCache<Key, Value>();
        this.memoryCache = new MemoryCache<Key, Value>();
        this.numberOfRequests = 0;
    }

    /**
     * Кешируем только в ram.
     * Принимает ключ и значение.
     */
    @Override
    public void cache(Key key, Value value) throws IOException, ClassNotFoundException {
        ramCache.cache(key, value);
    }

    /**
     * Проверяет наличие объекта на двух уровнях кеша,
     * если находит, возвращает его.
     */
    @Override
    public Value getObject(Key key) throws IOException, ClassNotFoundException {
        if (ramCache.containsKey(key)) {
            doRecache();
            return ramCache.getObject(key);
        }

        if (memoryCache.containsKey(key)) {
            doRecache();
            return memoryCache.getObject(key);
        }
        return null;
    }

    /**
     * Инкрементирует количество возовов объекта, при необходимости
     * вызывает recache()
     * <p>
     * прим. несколько помогает избежать дублирования кода в методе doRecache()
     * собственно, работает в паре с этим методом.
     */
    private void doRecache() throws IOException, ClassNotFoundException {
        numberOfRequests++;
        if (numberOfRequests > requestsForRecahce) {
            this.recache();
            numberOfRequests = 0;
        }
    }

    @Override
    public void deleteObject(Key key) {
        if (ramCache.containsKey(key)) {
            ramCache.deleteObject(key);
        }
        if (memoryCache.containsKey(key)) {
            memoryCache.deleteObject(key);
        }
    }


    @Override
    public void clearCache() {
        memoryCache.clearCache();
        ramCache.clearCache();
    }


    @Override
    public Value removeObject(Key key) throws IOException, ClassNotFoundException {
        if (ramCache.containsKey(key)) {
            return ramCache.removeObject(key);
        }
        if (memoryCache.containsKey(key)) {
            return memoryCache.removeObject(key);
        }
        return null;
    }


    @Override
    public boolean containsKey(Key key) {
        if (ramCache.containsKey(key)) {
            return true;
        }
        if (memoryCache.containsKey(key)) {
            return true;
        }
        return false;
    }


    @Override
    public int size() {
        return ramCache.size() + memoryCache.size();
    }

    public int ramSize(){
        return ramCache.size();
    }

    public int memorySize(){
        return memoryCache.size();
    }

    /**
     * 1. При рекэшировании находится среднее арифметическое количества вызовов каждого объекта
     * 2. Редко используемые объекты переносятся из оперативной памяти, на жесткий диск.
     * 3. И наоборот, все объекты которые часто используются, хранящиеся на жестком диске,
     *    забрасываются в оперативную память.
     * <p>
     * Происходит постоянное перетасовывание объектов между двумя кэшами.
     */
    @Override
    public void recache() throws IOException, ClassNotFoundException {
        TreeSet<Key> ramKeySet = new TreeSet<Key>(ramCache.getMostCalledKeys());
        int boundFrecquency = 0;

        // 1.
        for (Key key : ramKeySet) {
            boundFrecquency += ramCache.getNumberOfCallsToObject(key);
        }
        boundFrecquency /= ramKeySet.size();

        // 2.
        for (Key key : ramKeySet) {
            if (ramCache.getNumberOfCallsToObject(key) <= boundFrecquency) {
                memoryCache.cache(key, ramCache.removeObject(key));
            }
        }

        // 3.
        TreeSet<Key> memoryKeySet = new TreeSet<Key>(memoryCache.getMostCalledKeys());
        for (Key key : memoryKeySet) {
            try {
                if (memoryCache.getNumberOfCallsToObject(key) > boundFrecquency) {
                    ramCache.cache(key, memoryCache.removeObject(key));
                }
            } catch (IOException ex) {
                memoryCache.deleteObject(key);
                continue;
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                continue;
            }
        }
    }

    @Override
    public Set<Key> getMostCalledKeys() {
        TreeSet<Key> set = new TreeSet<Key>(ramCache.getMostCalledKeys());
        set.addAll(memoryCache.getMostCalledKeys());
        return set;
    }

    @Override
    public int getNumberOfCallsToObject(Key key) {
        if (ramCache.containsKey(key)) {
            return ramCache.getNumberOfCallsToObject(key);
        }
        if (memoryCache.containsKey(key)) {
            return memoryCache.getNumberOfCallsToObject(key);
        }
        return 0;
    }
}
