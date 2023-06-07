package task_5_3;

public class Main {
    /*
    * Реализовать функцию, которая изменить двоичное дерево следующим образом:
Присоединит к самому правому листу в дереве в качестве правого потомка левое
поддерево корня дерева (левый потомок у корня дерева при этом надо убрать).
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
        tree.leftToRight();
        System.out.println("Конечное дерево, после переподвешивания левого поддерева корня к правому листу:");
        System.out.println(tree.toBracketStr());

    }
}
