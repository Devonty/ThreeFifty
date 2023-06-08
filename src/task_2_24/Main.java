package task_2_24;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        Random rd = new Random();
        int len = 30;
        // Initialization
        System.out.println("Исходниый случайный Лист:");
        for (int i = 0; i < len; i++) {
            list.addLast(rd.nextInt(-5, 5));
            System.out.print(list.getLast() + " ");
        }
        System.out.println();

        // special points
        SimpleLinkedList<Integer>.SimpleLinkedListNode<Integer> lastNeg = null;
        SimpleLinkedList<Integer>.SimpleLinkedListNode<Integer> lastZero = null;
        SimpleLinkedList<Integer>.SimpleLinkedListNode<Integer> lastPos = null;

        SimpleLinkedList<Integer>.SimpleLinkedListNode<Integer> firstNeg = null;
        SimpleLinkedList<Integer>.SimpleLinkedListNode<Integer> firstZero = null;
        SimpleLinkedList<Integer>.SimpleLinkedListNode<Integer> firstPos = null;

        SimpleLinkedList<Integer>.SimpleLinkedListNode<Integer> current = list.getHead();
        // Строим крайние позиции для каждой группы чисел
        while (current != null) {
            int value = current.value;
            if (value < 0) { // POSITIVE
                if (firstNeg == null) {
                    firstNeg = current;
                    lastNeg = current;
                } else {
                    lastNeg.next = current;
                    lastNeg = current;
                }
            }
            else if (value == 0) { // ZEROES
                if (firstZero == null) {
                    firstZero = current;
                    lastZero = current;
                } else {
                    lastZero.next = current;
                    lastZero = current;
                }
            }
            else { // POSITIVE
                if (firstPos == null) {
                    firstPos = current;
                    lastPos = current;
                } else {
                    lastPos.next = current;
                    lastPos = current;
                }
            }
            current = current.next;
        }

        // Сшиваем
        if(firstNeg != null) {
            list.setHead(firstNeg);
            if(firstZero != null) {
                lastNeg.next = firstZero;
                lastZero.next = firstPos;
            }
            else{
                lastNeg.next = firstPos;
            }
        } else if(firstZero != null) {
            list.setHead(firstZero);
            lastZero.next = firstPos;
        } else{
            list.setHead(firstPos);
        }

        System.out.println("Носле манипуляций:");
        for (int i = 0; i < len; i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }
}
