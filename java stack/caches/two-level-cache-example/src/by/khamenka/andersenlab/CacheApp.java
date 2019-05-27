package by.khamenka.andersenlab;

import by.khamenka.andersenlab.caches.TwoLevelCache;
import by.khamenka.andersenlab.object.MyFile;

import java.io.IOException;

// Демонстрация работы
public class CacheApp {
    private final static int MAX_RAM_CACHE_CAPACITY = 10;
    private final static int REQUESTS_FOR_CACHE = 3;

    /**
     * Это простая демонстрация работы кеша. Реализация демонстрации не может и не должна быть руководством
     * к использованию данного кеша.
     *
     * Если у вас есть вопросы о работе и/или реализации данного кеша и вы решили им воспользоватся
     * или у вас есть любые иные вопросы относительно данного предмета. Вы можете задать их сюда:
     * khamenka.yaraslau@gmail.com
     * */
    public static void main(String[] args) {
        TwoLevelCache twoLevelCache = new TwoLevelCache(MAX_RAM_CACHE_CAPACITY, REQUESTS_FOR_CACHE);

        try {

            /*
             * Для каждой итерации цикла:
             * Добавить в кеш новый файл (pojo) где в выражении ("" + i, new MyFile("" + i, i))
             * "" + i ---> значение ключа, для каждого файла значение ключае равняется номеру итерации
             *             на которой происходит добавление файла
             * new MyFile("" + i, i) ---> создание нового файла, здесь номер итераци это одновременно и
             *                            имя этого pojo (храниться в одном из полей) и значение, которое в
             *                            этом файле храниться.
             *
             * Для каждой третей итерации:
             * Имитируем обращение объекта из кеша (начиная с обращения к первому добавленному элементу i-2)
             * узнаём количество объектов в кеше
             */
            for (int i = 0; i < 21; i++) {
                twoLevelCache.cache("" + i, new MyFile("" + i, i));
                if(i%3 == 0){
                    System.out.println(twoLevelCache.getObject(""+(i-2)));
                    checkCacheSize(twoLevelCache);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            twoLevelCache.clearCache();
        }


    }

    public static void checkCacheSize(TwoLevelCache cache) {
        System.out.println("------");
        System.out.println("Ram: " + cache.ramSize());
        System.out.println("Memory: " + cache.memorySize());
        System.out.println("------");
    }
}
