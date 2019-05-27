package by.khamenka.andersenlab.caches;

import by.khamenka.andersenlab.utils.CallCountComparator;
import by.khamenka.andersenlab.interfaces.Cache;
import by.khamenka.andersenlab.interfaces.CallObjectCounter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Второй уровень кеша (память на жестком диске).
 * Объекты буду храниться в папке temp
 */
public class MemoryCache<Key, Value extends Serializable>
        implements Cache<Key, Value>, CallObjectCounter<Key> {

    /**
     * Коллекции используються аналогично коллекциям из RamCache
     */
    HashMap<Key, String> hashMap;
    TreeMap<Key, Integer> frequencyMap;


    /**
     * Создаём временую папку в которой будут храниться объекты
     * второго уровня кеша.
     */
    public MemoryCache() {
        hashMap = new HashMap<Key, String>();
        frequencyMap = new TreeMap<Key, Integer>();

        File tempFolder = new File("temp/");
        if (!tempFolder.exists()) {
            tempFolder.mkdirs();
        }
    }

    /**
     * 1. Метод UUID.randomUUID().toString() используется для получения
     * уникального идентификационного имени для хранения на жестком диске в папке temp.
     * <P> вообще классный Класс! Раньше, как альтернативой, пользовался бы currentTime
     * 2. В hashMap помещается полный путь до этого объекта, а в качестве ключа используется
     * хэш этого объекта в системе.
     */
    @Override
    public void cache(Key key, Value value) throws IOException {
        String pathToObject;
        pathToObject = "temp/" + UUID.randomUUID().toString() + ".temp";

        frequencyMap.put(key, 1);
        hashMap.put(key, pathToObject);

        FileOutputStream fileStream = new FileOutputStream(pathToObject);
        ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

        objectStream.writeObject(value);
        objectStream.flush();
        objectStream.close();
        fileStream.flush();
        fileStream.close();
    }

    /**
     * 1. Получаем по ключу адрес файла на жестком диске из hashMap
     * 2. Читаем его
     * 3. Десериализуем
     * 4. Увеличиваем на один частоту вызова
     * 5. Очищаем потоки.
     */
    @Override
    public Value getObject(Key key) throws IOException, ClassNotFoundException {
        if (hashMap.containsKey(key)) {
            // 1.
            String pathToObject = hashMap.get(key);

            try {
                // 2.
                FileInputStream fileStream = new FileInputStream(pathToObject);
                ObjectInputStream objectStream = new ObjectInputStream(fileStream);

                // 3.
                Value deserializedObject = (Value) objectStream.readObject();

                // 4.
                int frecquency = frequencyMap.remove(key);
                frequencyMap.put(key, ++frecquency);

                // 5.
                fileStream.close();
                objectStream.close();

                return deserializedObject;
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }


    @Override
    public void deleteObject(Key key) {
        if (hashMap.containsKey(key)) {
            File deletingFile = new File(hashMap.remove(key));
            frequencyMap.remove(key);
            deletingFile.delete();
        }
    }


    @Override
    public void clearCache() {
        for (Key key : hashMap.keySet()) {
            File deletingFile = new File(hashMap.get(key));
            deletingFile.delete();
        }

        hashMap.clear();
        frequencyMap.clear();
    }


    @Override
    public Value removeObject(Key key) throws IOException, ClassNotFoundException {
        if (hashMap.containsKey(key)) {
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
        CallCountComparator comparator = new CallCountComparator(frequencyMap);
        TreeMap<Key, Integer> sorted = new TreeMap(comparator);
        sorted.putAll(frequencyMap);
        return sorted.keySet();
    }


    @Override
    public int getNumberOfCallsToObject(Key key) {
        if (hashMap.containsKey(key)) {
            return frequencyMap.get(key);
        }
        return 0;
    }
}




