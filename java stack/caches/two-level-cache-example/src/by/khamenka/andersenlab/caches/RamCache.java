package by.khamenka.andersenlab.caches;

import by.khamenka.andersenlab.utils.CallCountComparator;
import by.khamenka.andersenlab.interfaces.Cache;
import by.khamenka.andersenlab.interfaces.CallObjectCounter;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

/**
 * Первый уровень кеша (оперативная память).
 * В основе лежит HashMap.
 */
public class RamCache<Key, Value> implements Cache<Key, Value>, CallObjectCounter<Key> {
    /**
     * Это, собственно кеш.
     */
    private HashMap<Key, Value> hashMap;

    /**
     * Здесь храним частоту вызовов объектов из кеша.
     */
    private TreeMap<Key, Integer> CallCounterMap;


    public RamCache(){
        hashMap = new HashMap<Key, Value>();
        CallCounterMap = new TreeMap<Key, Integer>();
    }

    /**
     * Частота вызовов по-умолчанию равна '1'.
     */
    @Override
    public void cache(Key key, Value value) {
        CallCounterMap.put(key,1);
        hashMap.put(key, value);
    }

    /**
     * Если кто-то получил ссылку на объект, увеличиваем количество вызовов.
     * */
    @Override
    public Value getObject(Key key) {
        if(hashMap.containsKey(key)){
            int frequency = CallCounterMap.get(key);
            CallCounterMap.put(key,++frequency);
            return hashMap.get(key);
        }
        return null;
    }

    @Override
    public void deleteObject(Key key) {
        if(hashMap.containsKey(key)){
            hashMap.remove(key);
            CallCounterMap.remove(key);
        }
    }

    @Override
    public void clearCache() {
        hashMap.clear();
        CallCounterMap.clear();
    }

    @Override
    public Value removeObject(Key key) {
        if(hashMap.containsKey(key)){
            Value result = this.getObject(key);
            this.deleteObject(key);
            return result;
        }
        return null;
    }

    @Override
    public boolean containsKey(Key key) {
        return hashMap.containsKey(key);
    }

    @Override
    public int size() {
        return hashMap.size();
    }

    @Override
    public Set<Key> getMostCalledKeys() {
        CallCountComparator comparator = new CallCountComparator(CallCounterMap);
        TreeMap<Key,Integer> sorted = new TreeMap(comparator);
        sorted.putAll(CallCounterMap);
        return sorted.keySet();
    }

    @Override
    public int getNumberOfCallsToObject(Key key) {
        if(hashMap.containsKey(key)){
            return CallCounterMap.get(key);
        }
        return 0;
    }
}

