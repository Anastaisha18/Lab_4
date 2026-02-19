package lab4;

public class Box<T> {
    private T item;

    public Box() {
        this.item = null;
    }

    public void put(T item) throws IllegalStateException {
        if (isFull()) {
            throw new IllegalStateException("Коробка уже заполнена!");
        }
        this.item = item;
        System.out.println("\nВ коробку положен объект: " + item);
    }

    public T take() {
        if (isEmpty()) {
            System.out.println("Коробка пуста");
            return null;
        }
        T takenItem = this.item;
        this.item = null;
        System.out.println("Из коробки взят объект: " + takenItem);
        return takenItem;
    }

    public boolean isFull() {
        return item != null;
    }

    public boolean isEmpty() {
        return item == null;
    }

    public T peek() {
        return item;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Коробка: пуста";
        } else {
            return "Коробка: содержит [" + item + "]";
        }
    }
}