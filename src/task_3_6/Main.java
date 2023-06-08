package task_3_6;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        SimpleQueue<Double> X = new SimpleLinkedListQueue<>();
        SimpleQueue<Double> Y = new SimpleLinkedListQueue<>();


        // Реализация со встроеными
        // Queue<String> queue1 = new LinkedList<>();
        // Queue<String> queue2 = new LinkedList<>();

        // Объявление
        int n = 6, m = 4;
        Random rd = new Random();
        // Объявление queue1
        for (int i = 0; i < n; i++) {
            X.add(rd.nextDouble(-5, 5));
        }
        // Объявление queue2
        for (int i = 0; i < m; i++) {
            Y.add(rd.nextDouble(-5, 5));
        }
        String now;
        // Вывод queue1
        printQueue(X, "Очередь 1:");
        // Вывод queue2
        printQueue(Y, "Очередь 2:");

        // simulation
        int count = 0;
        while (!X.empty() && !Y.empty()) {
            double x = X.remove();
            double y = Y.remove();
            if (x < y) {
                X.add(x + y);
                System.out.println("Добавляем к первой (x+y) = " + (x + y));
            } else {
                Y.add(x - y);
                System.out.println("Добавляем ко второй (x-y) = " + (x - y));
            }

            count++;
        }


        printSplit();
        // Вывод queue1
        printQueue(X, "Очередь 1:");
        // Вывод queue2
        printQueue(Y, "Очередь 2:");
        System.out.println("Число шагов: " + count);


    }

    public static <T> void printQueue(SimpleQueue<T> queue, String title) {
        System.out.println(title);
        T now;
        for (int i = 0; i < queue.count(); i++) {
            now = queue.remove();
            System.out.print(now + " ");
            queue.add(now);
        }
        System.out.println();
    }

    public static <T> void printQueue(Queue<T> queue, String title) {
        System.out.println(title);
        T now;
        for (int i = 0; i < queue.size(); i++) {
            now = queue.remove();
            System.out.print(now + " ");
            queue.add(now);
        }
        System.out.println();
    }

    public static void printSplit() {
        System.out.println("-".repeat(30));
    }
}
