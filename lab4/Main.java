package lab4;

import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("ЛАБОРАТОРНАЯ РАБОТА: ОБОБЩЕННЫЕ ТИПЫ");

        while (true) {
            System.out.println("\nМЕНЮ:");
            System.out.println("1  №1.1 Коробка (Box)");
            System.out.println("2  №1.2 Хранилище (Storage)");
            System.out.println("3  №2.3 Коробка с Point3D");
            System.out.println("4  №3.1 Преобразование списков (map)");
            System.out.println("5  №3.2 Фильтрация списков (filter)");
            System.out.println("6  №3.3 Сокращение списков (reduce)");
            System.out.println("7  №3.4 Коллекционирование");
            System.out.println("0  Выход");

            int choice = readInt("Выберите пункт: ", 0, 7);  // Исправлено: 0-7 вместо 1-8

            switch (choice) {
                case 1: demoBox(); break;
                case 2: demoStorage(); break;
                case 3: demoPoint3D(); break;
                case 4: demoMap(); break;
                case 5: demoFilter(); break;
                case 6: demoReduce(); break;
                case 7: demoCollect(); break;
                case 0: System.out.println("До свидания!"); return;
            }

            System.out.println("\nНажмите Enter...");
            scanner.nextLine();
        }
    }
    private static void processBox(Box<Integer> box) {
        if (!box.isEmpty()) {
            Integer value = box.take();
            System.out.println("В методе processBox извлекли: " + value);
        } else {
            System.out.println("Коробка пуста");
        }
    }
    private static void demoBox() {
        System.out.println("\n--- КОРОБКА ---");
        Box<Integer> box = new Box<>();

        // Сразу кладем число 3 для демонстрации
        try {
            box.put(3);
            System.out.println("Положили число 3 в коробку");

            // ПЕРЕДАЕМ КОРОБКУ В МЕТОД (ТРЕБОВАНИЕ ЗАДАНИЯ)
            System.out.println("\n--- Передаем коробку в метод processBox ---");
            processBox(box);
            System.out.println("--- Коробка после метода ---");
            System.out.println(box);
            System.out.println("--- Возврат в меню ---\n");

        } catch (IllegalStateException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        while (true) {
            System.out.println("\n1. Положить объект");
            System.out.println("2. Достать объект");
            System.out.println("3. Проверить коробку");
            System.out.println("4. Назад в меню");

            int choice = readInt("Выберите действие: ", 1, 4);

            switch (choice) {
                case 1:
                    int putValue = readInt("Какой объект положить? ");
                    try {
                        box.put(putValue);
                        System.out.println("Объект " + putValue + " положен в коробку");
                    } catch (IllegalStateException e) {
                        System.out.println("Ошибка: " + e.getMessage() + " Сначала достаньте объект " + box.peek());
                    }
                    break;

                case 2:
                    if (box.isEmpty()) {
                        System.out.println("Коробка пуста, нечего доставать");
                    } else {
                        Integer taken = box.take();
                        System.out.println("Вы достали из коробки: " + taken);
                    }
                    break;

                case 3:
                    System.out.println(box);
                    break;

                case 4:
                    return;
            }
        }
    }

    private static void demoStorage() {
        System.out.println("\n--- ХРАНИЛИЩЕ (STORAGE) ---");

        while (true) {
            System.out.println("\n1. Хранилище чисел с null (альтернатива 0)");
            System.out.println("2. Хранилище чисел с 99 (альтернатива -1)");
            System.out.println("3. Хранилище строк с null (альтернатива default)");
            System.out.println("4. Хранилище строк с hello (альтернатива hello world)");
            System.out.println("5. В главное меню");

            int choice = readInt("Выберите: ", 1, 5);

            switch (choice) {
                case 1:
                    Storage<Integer> s1 = new Storage<>(null);
                    System.out.println("Ссылка на объект: " + s1.getItem());
                    processIntStorage(s1, 0);
                    System.out.println("\nНажмите Enter чтобы продолжить...");
                    scanner.nextLine();
                    break;
                case 2:
                    Storage<Integer> s2 = new Storage<>(99);
                    System.out.println("Ссылка на объект: " + s2.getItem());
                    processIntStorage(s2, -1);
                    System.out.println("\nНажмите Enter чтобы продолжить...");
                    scanner.nextLine();
                    break;
                case 3:
                    Storage<String> s3 = new Storage<>(null);
                    System.out.println("Ссылка на объект: " + s3.getItem());
                    processStringStorage(s3, "default");
                    System.out.println("\nНажмите Enter чтобы продолжить...");
                    scanner.nextLine();
                    break;
                case 4:
                    Storage<String> s4 = new Storage<>("hello");
                    System.out.println("Ссылка на объект: " + s4.getItem());
                    processStringStorage(s4, "hello world");
                    System.out.println("\nНажмите Enter чтобы продолжить...");
                    scanner.nextLine();
                    break;
                case 5:
                    return;
            }
        }
    }

    private static void processIntStorage(Storage<Integer> storage, int defaultValue) {
        Integer value = storage.getOrDefault(defaultValue);
        System.out.println("Извлечено значение: " + value);
    }

    private static void processStringStorage(Storage<String> storage, String defaultValue) {
        String value = storage.getOrDefault(defaultValue);
        System.out.println("Извлечено значение: \"" + value + "\"");
    }

    private static void demoPoint3D() {
        System.out.println("\n--- ТОЧКА В КОРОБКЕ ---");
        double x = readDouble("x: ");
        double y = readDouble("y: ");
        double z = readDouble("z: ");
        Point3D p = new Point3D(x, y, z);

        Box<Point3D> box1 = new Box<>();
        putPointInBox(box1, p);
        System.out.println("Из box1: " + box1.take());

        Box<Point2D> box2 = new Box<>();
        putPointInBox(box2, p);
        System.out.println("Из box2: " + box2.take());

        Box<Object> box3 = new Box<>();
        putPointInBox(box3, p);
        System.out.println("Из box3: " + box3.take());
    }

    private static void putPointInBox(Box<? super Point3D> box, Point3D point) {
        box.put(point);
    }

    private static void demoMap() {
        System.out.println("\n--- MAP ---");
        List<String> strings = readStringList("Строки: ");
        System.out.println("Длины: " + ListUtils.map(strings, String::length));

        List<Integer> nums = readIntList("Числа: ");
        System.out.println("Модули: " + ListUtils.map(nums, Math::abs));

        List<int[]> arrays = readArrays();
        System.out.println("Максимумы: " + ListUtils.map(arrays, ListUtils::findMax));  // Исправлено: FunctionDemo -> ListUtils
    }

    private static void demoFilter() {
        System.out.println("\n--- FILTER ---");
        List<String> strings = readStringList("Строки: ");
        System.out.println("Строки с длиной < 3: " + ListUtils.filter(strings, s -> s.length() < 3));

        List<Integer> nums = readIntList("Числа: ");
        System.out.println("Положительные: " + ListUtils.filter(nums, n -> n > 0));

        List<int[]> arrays = readArrays();
        System.out.println("Без положительных:");
        printArrays(ListUtils.filter(arrays, ListUtils::hasNoPositives));  // Исправлено: FunctionDemo -> ListUtils
    }

    private static void demoReduce() {
        System.out.println("\n--- REDUCE ---");

        // 1. Конкатенация строк
        List<String> strings = readStringList("Введите строки через пробел: ");
        if (strings.isEmpty()) {
            System.out.println("Список строк пуст");
        } else {
            System.out.println("Конкатенация: \"" + ListUtils.reduce(strings, (a,b)->a+b, "") + "\"");
        }

        // 2. Сумма чисел
        List<Integer> nums = readIntList("Введите числа через пробел: ");
        if (nums.isEmpty()) {
            System.out.println("Список чисел пуст");
        } else {
            System.out.println("Сумма: " + ListUtils.reduce(nums, Integer::sum, 0));
        }

        // 3. Общее количество элементов во вложенных списках
        List<List<Integer>> lists = readListOfLists();
        if (lists.isEmpty()) {
            System.out.println("Нет списков для подсчета");
        } else {
            List<Integer> sizes = ListUtils.map(lists, List::size);
            System.out.println("Всего элементов: " + ListUtils.reduce(sizes, Integer::sum, 0));
        }
    }

    private static void demoCollect() {
        System.out.println("\n--- COLLECT ---");
        List<Integer> nums = readIntList("Числа: ");
        List<Integer> positive = ListUtils.filter(nums, n -> n > 0);
        List<Integer> negative = ListUtils.filter(nums, n -> n < 0);

        System.out.println("Положительные: " + positive);
        System.out.println("Отрицательные: " + negative);

        List<String> strings = readStringList("Строки: ");
        Map<Integer, List<String>> groups = ListUtils.groupBy(strings, String::length);
        System.out.println("По длине: " + groups);

        Set<String> set = new HashSet<>(ListUtils.collectToCollection(strings, HashSet::new));
        System.out.println("Уникальные: " + set);
    }


    // Вспомогательные методы ввода
    private static int readInt(String prompt) { return readInt(prompt, Integer.MIN_VALUE, Integer.MAX_VALUE); }

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int val = Integer.parseInt(scanner.nextLine().trim());
                if (val >= min && val <= max) return val;
                System.out.println("Ошибка: от " + min + " до " + max);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число");
            }
        }
    }

    private static List<String> readStringList(String prompt) {
        System.out.print(prompt);
        String line = scanner.nextLine().trim();
        return line.isEmpty() ? new ArrayList<>() : Arrays.asList(line.split("\\s+"));
    }

    private static List<Integer> readIntList(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) return new ArrayList<>();
            try {
                return Arrays.stream(line.split("\\s+")).map(Integer::parseInt).toList();
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целые числа");
            }
        }
    }

    private static List<int[]> readArrays() {
        List<int[]> arrays = new ArrayList<>();
        int count = readInt("Количество массивов: ", 1, 5);
        for (int i = 0; i < count; i++) {
            System.out.print("Массив " + (i+1) + ": ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                arrays.add(new int[0]);
                continue;
            }
            try {
                arrays.add(Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).toArray());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка, пропускаем");
                i--;
            }
        }
        return arrays;
    }

    private static List<List<Integer>> readListOfLists() {
        List<List<Integer>> result = new ArrayList<>();
        int count = readInt("Количество списков: ", 1, 3);
        for (int i = 0; i < count; i++) {
            System.out.print("Список " + (i+1) + ": ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                result.add(new ArrayList<>());
                continue;
            }
            try {
                result.add(Arrays.stream(line.split("\\s+")).map(Integer::parseInt).toList());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка, пропускаем");
                i--;
            }
        }
        return result;
    }

    private static void printArrays(List<int[]> arrays) {
        for (int[] arr : arrays) System.out.println("  " + Arrays.toString(arr));
    }
}