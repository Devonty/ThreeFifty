package task_4_11;

import java.util.Comparator;
import java.util.List;

/**
 * Класс с реализацией пирамидальной cортировки (HeapSort) для массивов и списков
 */
public class HeapSort {

    /**
     * Процедура просеивания элемента [k] в пирамиде размера n.
     * <p>
     * До процедуры элементы [k + 1] .. [n - 1] удовлетворяют условиям пирамиды
     * размера n. После процедуры элементы [k] .. [n - 1] удовлетворяют условиям
     * пирамиды размера n.</p>
     *
     * @param <T> Произвольный (но ссылочный) тип элементов массива
     * @param data Массив типа T
     * @param k Элемент, который "просеивается"
     * @param n Размер пирамиды
     * @param c Компаратор для сравнения элементов
     */
    private static <T> void siftDown(T[] data, int k, int n, Comparator<T> c) {
        T value = data[k];
        while (true) {  // пока у a[k] есть потомки в пирамиде размера n
            int childIndex = 2 * k + 1;
            // останавливаемся, если в пирамиде размером n у [k] нет потомков
            if (childIndex >= n) {
                break;
            }
            //  выбираем большего потомка
            if (childIndex + 1 < n && c.compare(data[childIndex + 1], data[childIndex]) > 0) {
                childIndex++;
            }
            // останавливаетмся, если добавляемый элемент больше очередного потомка
            if (c.compare(value, data[childIndex]) > 0) {
                break;
            }
            // иначе
            data[k] = data[childIndex];  // переносим потомка наверх
            k = childIndex;
        }
        data[k] = value;  // устанавливаем "просеиваемый" элемен на свое место
    }

    /**
     * Пирамидальная сортировка (task_4_11.HeapSort)
     *
     * @param <T> Произвольный (но ссылочный) тип элементов массива
     * @param data Сортируемый массив типа T
     * @param c Компаратор для сравнения элементов
     */
    public static <T> void sort(T[] data, Comparator<T> c) {
        int heapSize = data.length;

        // начальное построение пирамиды:
        // начинаем с heapSize / 2, т.к. это последний элемент в пирамиде,
        // у которого могут быть потомки
        for (int i = heapSize / 2; i >= 0; i--) {
            siftDown(data, i, heapSize, c);
        }

        // собственно сортировка:
        // в цикле обмен [0] c последним элементом и просеивание [0]
        while (heapSize > 1) {
            // перенос (обмен) вершины стека [0] в конец сортируемой части массива [heapSize - 1]
            T tmp = data[heapSize - 1];
            data[heapSize - 1] = data[0];
            data[0] = tmp;
            // уменьшаем размер пирамиды
            heapSize--;
            // просеиваем элемент [0]
            siftDown(data, 0, heapSize, c);
        }
    }

    /**
     * Пирамидальная сортировка (без компаратора для сравнимых элементов)
     *
     * @param <T> Сравнимый тип элементов массива
     * @param data Сортируемый массив типа T
     */
    public static <T extends Comparable<T>> void sort(T[] data) {
        sort(data, Comparable::compareTo);
    }





    /**
     * Процедура просеивания элемента [k] в пирамиде размера n.
     * <p>
     * До процедуры элементы [k + 1] .. [n - 1] удовлетворяют условиям пирамиды
     * размера n. После процедуры элементы [k] .. [n - 1] удовлетворяют условиям
     * пирамиды размера n.</p>
     *
     * @param data Массив типа int
     * @param k Элемент, который "просеивается"
     * @param n Размер пирамиды
     */
    private static void siftDown(int[] data, int k, int n) {
        int value = data[k];
        while (true) {  // пока у a[k] есть потомки в пирамиде размера n
            int childIndex = 2 * k + 1;
            // останавливаемся, если в пирамиде размером n у [k] нет потомков
            if (childIndex >= n) {
                break;
            }
            //  выбираем большего потомка
            if (childIndex + 1 < n && data[childIndex + 1] > data[childIndex]) {
                childIndex++;
            }
            // останавливаетмся, если добавляемый элемент больше очередного потомка
            if (value > data[childIndex]) {
                break;
            }
            // иначе
            data[k] = data[childIndex];  // переносим потомка наверх
            k = childIndex;
        }
        data[k] = value;  // устанавливаем "просеиваемый" элемен на свое место
    }

    /**
     * Пирамидальная сортировка (task_4_11.HeapSort) (в качестве примера)
     *
     * @param data Сортируемый массив типа T
     */
    public static void sort(int[] data) {
        int heapSize = data.length;

        // начальное построение пирамиды:
        // начинаем с heapSize / 2, т.к. это последний элемент в пирамиде,
        // у которого могут быть потомки
        for (int i = heapSize / 2; i >= 0; i--) {
            siftDown(data, i, heapSize);
        }

        // собственно сортировка:
        // в цикле обмен [0] c последним элементом и просеивание [0]
        while (heapSize > 1) {
            // перенос (обмен) вершины стека [0] в конец сортируемой части массива [heapSize - 1]
            int tmp = data[heapSize - 1];
            data[heapSize - 1] = data[0];
            data[0] = tmp;
            // уменьшаем размер пирамиды
            heapSize--;
            // просеиваем элемент [0]
            siftDown(data, 0, heapSize);
        }
    }





    /**
     * Процедура просеивания элемента [k] в пирамиде размера n.
     * <p>
     * До процедуры элементы [k + 1] .. [n - 1] удовлетворяют условиям пирамиды
     * размера n. После процедуры элементы [k] .. [n - 1] удовлетворяют условиям
     * пирамиды размера n.</p>
     *
     * @param <T> Произвольный (но ссылочный) тип элементов списка
     * @param data Список типа T
     * @param k Элемент, который "просеивается"
     * @param n Размер пирамиды
     * @param c Компаратор для сравнения элементов
     */
    private static <T> void siftDown(List<T> data, int k, int n, Comparator<T> c) {
        T value = data.get(k);
        while (true) {  // пока у a[k] есть потомки в пирамиде размера n
            int childIndex = 2 * k + 1;
            // останавливаемся, если в пирамиде размером n у [k] нет потомков
            if (childIndex >= n) {
                break;
            }
            T childValue = data.get(childIndex + 1);
            //  выбираем большего потомка
            if (childIndex + 1 < n) {
                T rightChildValue = data.get(childIndex + 1);
                if (c.compare(rightChildValue, childValue) > 0) {
                    childIndex++;
                    childValue = rightChildValue;
                }
            }
            // останавливаетмся, если добавляемый элемент больше очередного потомка
            if (c.compare(value, childValue) > 0) {
                break;
            }
            // иначе
            data.set(k, childValue);  // переносим потомка наверх
            k = childIndex;
        }
        data.set(k, value);  // устанавливаем "просеиваемый" элемен на свое место
    }

    /**
     * Пирамидальная сортировка (task_4_11.HeapSort)
     *
     * @param <T> Произвольный (но ссылочный) тип элементов списка
     * @param data Сортируемый список типа T
     * @param c Компаратор для сравнения элементов
     */
    public static <T> void sort(List<T> data, Comparator<T> c) {
        int heapSize = data.size();

        // начальное построение пирамиды:
        // начинаем с heapSize / 2, т.к. это последний элемент в пирамиде,
        // у которого могут быть потомки
        for (int i = heapSize / 2; i >= 0; i--) {
            siftDown(data, i, heapSize, c);
        }

        // собственно сортировка:
        // в цикле обмен [0] c последним элементом и просеивание [0]
        while (heapSize > 1) {
            // перенос (обмен) вершины стека [0] в конец сортируемой части массива [heapSize - 1]
            T tmp = data.get(heapSize - 1);
            data.set(heapSize - 1, data.get(0));
            data.set(0, tmp);
            // уменьшаем размер пирамиды
            heapSize--;
            // просеиваем элемент [0]
            siftDown(data, 0, heapSize, c);
        }
    }

    /**
     * Пирамидальная сортировка (без компаратора для сравнимых элементов)
     *
     * @param <T> Сравнимый тип элементов списка
     * @param data Сортируемый список типа T
     */
    public static <T extends Comparable<T>> void sort(List<T> data) {
        sort(data, (a, b) -> a.compareTo(b));
    }
}
