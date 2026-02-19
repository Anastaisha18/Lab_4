package lab4;

import java.util.*;
import java.util.function.*;

public class ListUtils {

    // 3.1 Функция (map) с встроенным Function
    public static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(function.apply(item));
        }
        return result;
    }

    // 3.2 Фильтр (filter) с встроенным Predicate
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    // 3.3 Сокращение (reduce) с встроенным BinaryOperator
    public static <T> T reduce(List<T> list, BinaryOperator<T> operator, T identity) {
        T result = identity;
        for (T item : list) {
            result = operator.apply(result, item);
        }
        return result;
    }

    // 3.4 Коллекционирование - группировка по ключу
    public static <T, K> Map<K, List<T>> groupBy(List<T> list, Function<T, K> classifier) {
        Map<K, List<T>> result = new HashMap<>();
        for (T item : list) {
            K key = classifier.apply(item);
            result.computeIfAbsent(key, k -> new ArrayList<>()).add(item);
        }
        return result;
    }

    // 3.4 Коллекционирование - разделение на две части по условию
    public static <T> Map<Boolean, List<T>> partitionBy(List<T> list, Predicate<T> predicate) {
        Map<Boolean, List<T>> result = new HashMap<>();
        result.put(true, new ArrayList<>());
        result.put(false, new ArrayList<>());

        for (T item : list) {
            result.get(predicate.test(item)).add(item);
        }
        return result;
    }

    // 3.4 Коллекционирование - сбор в указанную коллекцию
    public static <T, C extends Collection<T>> C collectToCollection(
            List<T> list, Supplier<C> collectionSupplier) {
        C result = collectionSupplier.get();
        result.addAll(list);
        return result;
    }

    // Вспомогательные методы
    public static int findMax(int[] array) {
        int max = array[0];
        for (int num : array) {
            if (num > max) max = num;
        }
        return max;
    }

    public static boolean hasNoPositives(int[] array) {
        for (int num : array) {
            if (num > 0) return false;
        }
        return true;
    }
}