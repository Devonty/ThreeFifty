package task_2_4;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        Random rd = new Random();
        int len = 30;
        for (int i = 0; i < len; i++) {
            list.addLast(rd.nextInt(0, 100));
            System.out.print(list.getLast() + " ");
        }
        System.out.println();

        int countMax = 1;
        int max = list.getFirst();
        for (int i = 0; i < len; i++) {
            if(list.get(i) > max){
                max = list.get(i);
                countMax = 1;
            } else if (list.get(i) == max) {
                countMax++;
            }
        }
        System.out.println("Максимум = " + max);
        System.out.println("Встретился " + countMax + " раз(а)");
    }
}
