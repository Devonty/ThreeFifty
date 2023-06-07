package task_3_3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        SimpleQueue<String> queue1 = new SimpleLinkedListQueue<>();
        SimpleQueue<String> queue2 = new SimpleLinkedListQueue<>();


        // Реализация со встроеными
        // Queue<String> queue1 = new LinkedList<>();
        // Queue<String> queue2 = new LinkedList<>();

        // Объявление
        int n = 6, m = 4;
        // Объявление queue1
        for (int i = 0; i < n; i++) {
            queue1.add(1 + "_" + i);
        }
        // Объявление queue2
        for (int i = 0; i < m; i++) {
            queue2.add(2 + "_" + i);
        }
        String now;
        // Вывод queue1
        printQueue(queue1, "Очередь 1:");
        // Вывод queue2
        printQueue(queue2, "Очередь 2:");
        // Перекидываю queue1 в конец queue2
        for (int i = 0; i < n; i++) {
            now = queue1.remove();
            queue1.add(now); // возвращаю
            queue2.add(now);
        }

        printSplit();
        // Вывод queue1
        printQueue(queue1, "Очередь 1:");
        // Вывод queue2
        printQueue(queue2, "Очередь 2:");

        for (int i = 0; i < m; i++) {
            now = queue2.remove();
            queue1.add(now);
            queue2.add(now); // возвращаю
        }

        printSplit();
        // Вывод queue1
        printQueue(queue1, "Очередь 1:");
        // Вывод queue2
        printQueue(queue2, "Очередь 2:");

        // Прокрутка очереди 2 на n
        for (int i = 0; i < n; i++) {
            now = queue2.remove();
            queue2.add(now); // возвращаю
        }

        printSplit();
        // Вывод queue1
        printQueue(queue1, "Очередь 1:");
        // Вывод queue2
        printQueue(queue2, "Очередь 2:");


    }

    public static <T> void printQueue(SimpleQueue<T> queue, String title){
        System.out.println(title);
        T now;
        for (int i = 0; i < queue.count(); i++) {
            now = queue.remove();
            System.out.print(now + " ");
            queue.add(now);
        }
        System.out.println();
    }

    public static <T> void printQueue(Queue<T> queue, String title){
        System.out.println(title);
        T now;
        for (int i = 0; i < queue.size(); i++) {
            now = queue.remove();
            System.out.print(now + " ");
            queue.add(now);
        }
        System.out.println();
    }

    public static void printSplit(){
        System.out.println("-".repeat(30));
    }
}
