package task_5_6;

import java.util.List;

public class Main {
    /*В заданном двоичном дереве поиска для целых чисел найти уровень, на котором сумма
элементов будет максимальна*/
    public static void main(String[] args) {
        SimpleBinaryTree<Integer> tree = new SimpleBinaryTree<>(Integer::parseInt);

        try {
            tree.fromBracketNotation("8 (6 (4 (5), 6), 5 (, 5 (2, 8)))");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Изначальное дерево: ");
        System.out.println(tree.toBracketStr());

        List<Number> list = tree.getMaxSumLevel();
        System.out.println("Уровень с максимальной суммой: " + list.get(0));
        System.out.println("Максимальная сумма: " + list.get(1));

    }
}
