package by.khamenka.andersenlab.utils;

import java.util.Comparator;
import java.util.Map;

/**
 * Используем для сортировки значений. И используеться в
 * CallObjectCounter.CallObjectCounter
 * <p>
 * Используя компаратор мы решаем проблему удаления ключей с одинаковой частотой вызовов.
 */
public class CallCountComparator implements Comparator {
    Map base;

    public CallCountComparator(Map base) {
        this.base = base;
    }

    @Override
    public int compare(Object o1, Object o2) {
        if((Integer)base.get(o1) < (Integer)base.get(o2)) {
            return 1;
        } else if((Integer)base.get(o1) == (Integer)base.get(o2)) {
            return 1;
        } else {
            return -1;
        }
    }
}

