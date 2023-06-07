package task_4_11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    static class Point {
        public double x;
        public double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        int len = 10;
        Point[] listHeapSort = new Point[len];
        Point[] listArraysSort = new Point[len];

        Random rd = new Random();
        System.out.println("Start Array: ");
        for (int i = 0; i < len; i++) {
            double x = rd.nextDouble(-200, 200);
            double y = rd.nextDouble(-200, 200);
            System.out.println("(" + x + ", " + y + ")");
            // заполняем одинаково
            listHeapSort[i] = new Point(x, y);
            listArraysSort[i] = new Point(x, y);
        }

        System.out.println("HeapSort:");
        HeapSort.sort(listHeapSort, Main::myComp);
        for (int i = 0; i < len; i++) {
            Point p = listHeapSort[i];
            System.out.println("(" + p.x + ", " + p.y + ") \t-> (dist) = " + getDistToZero(p));
        }

        System.out.println("ArraysSort:");
        Arrays.sort(listArraysSort, Main::myComp);
        for (int i = 0; i < len; i++) {
            Point p = listArraysSort[i];
            System.out.println("(" + p.x + ", " + p.y + ") \t-> (dist) = " + getDistToZero(p));
        }
    }

    public static double getDistToZero(Point p1) {
        double dX = p1.x;
        double dY = p1.y;
        return Math.sqrt(dX * dX + dY * dY);
    }

    public static int myComp(Point p1, Point p2) {
        return Double.compare(getDistToZero(p1), getDistToZero(p2));
    }
}
