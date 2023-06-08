package task_5_4;

public class Main {
    /*
    * Реализовать функцию, которая изменить двоичное дерево следующим образом:
Удалит все внутренние узлы, у которых есть только один потомок (соответственно,
потомки должны занять место удаляемых узлов).
*/
    public static void main(String[] args) {
        SimpleBinaryTree<Integer  > tree = new SimpleBinaryTree<>(Integer::parseInt);

        try {
            tree.fromBracketNotation("8 (6 (4 (5), 6), 5 (, 5 (2, 8)))");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Изначальное дерево: ");
        System.out.println(tree.toBracketStr());
        tree.clearOnes();
        System.out.println("Конечное дерево, после очистки:");
        System.out.println(tree.toBracketStr());

    }
}
