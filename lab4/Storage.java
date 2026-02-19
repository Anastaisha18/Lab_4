package lab4;

public class Storage<T> {
    private final T item;

    public Storage(T item) {
        this.item = item;
        System.out.println("Создано хранилище с объектом: " + (item != null ? item : "null"));
    }

    public T getOrDefault(T defaultValue) {
        if (item != null) {
            System.out.println("В хранилище найден объект: " + item);
            return item;
        } else {
            System.out.println("В хранилище null, возвращаем значение по умолчанию: " + defaultValue);
            return defaultValue;
        }
    }

    public boolean isNull() {
        return item == null;
    }

    public T getItem() {
        return item;
    }

    @Override
    public String toString() {
        if (item != null) {
            return "Хранилище: [" + item + "]";
        } else {
            return "Хранилище: [null]";
        }
    }
}