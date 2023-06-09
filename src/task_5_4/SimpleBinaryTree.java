package task_5_4;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;

/**
 * Реализация простейшего бинарного дерева
 */
public class SimpleBinaryTree<T> implements BinaryTree<T> {

    protected class SimpleTreeNode implements TreeNode<T> {
        public T value;
        public SimpleTreeNode left;
        public SimpleTreeNode right;

        public SimpleTreeNode(T value, SimpleTreeNode left, SimpleTreeNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public SimpleTreeNode(T value) {
            this(value, null, null);
        }

        @Override
        public T getValue() {
            return value;
        }

        @Override
        public TreeNode<T> getLeft() {
            return left;
        }

        @Override
        public TreeNode<T> getRight() {
            return right;
        }
    }

    protected SimpleTreeNode root = null;

    protected Function<String, T> fromStrFunc;
    protected Function<T, String> toStrFunc;

    public SimpleBinaryTree(Function<String, T> fromStrFunc, Function<T, String> toStrFunc) {
        this.fromStrFunc = fromStrFunc;
        this.toStrFunc = toStrFunc;
    }

    public SimpleBinaryTree(Function<String, T> fromStrFunc) {
        this(fromStrFunc, Object::toString);
    }

    public SimpleBinaryTree() {
        this(null);
    }

    @Override
    public TreeNode<T> getRoot() {
        return root;
    }

    public void clear() {
        root = null;
    }

    private T fromStr(String s) throws Exception {
        s = s.trim();
        if (s.length() > 0 && s.charAt(0) == '"') {
            s = s.substring(1);
        }
        if (s.length() > 0 && s.charAt(s.length() - 1) == '"') {
            s = s.substring(0, s.length() - 1);
        }
        if (fromStrFunc == null) {
            throw new Exception("Не определена функция конвертации строки в T");
        }
        return fromStrFunc.apply(s);
    }

    private static class IndexWrapper {
        public int index = 0;
    }

    private void skipSpaces(String bracketStr, IndexWrapper iw) {
        while (iw.index < bracketStr.length() && Character.isWhitespace(bracketStr.charAt(iw.index))) {
            iw.index++;
        }
    }

    private T readValue(String bracketStr, IndexWrapper iw) throws Exception {
        // пропуcкаем возможные пробелы
        skipSpaces(bracketStr, iw);
        if (iw.index >= bracketStr.length()) {
            return null;
        }
        int from = iw.index;
        boolean quote = bracketStr.charAt(iw.index) == '"';
        if (quote) {
            iw.index++;
        }
        while (iw.index < bracketStr.length() && (
                    quote && bracketStr.charAt(iw.index) != '"' ||
                    !quote && !Character.isWhitespace(bracketStr.charAt(iw.index)) && "(),".indexOf(bracketStr.charAt(iw.index)) < 0
               )) {
            iw.index++;
        }
        if (quote && bracketStr.charAt(iw.index) == '"') {
            iw.index++;
        }
        String valueStr = bracketStr.substring(from, iw.index);
        T value = fromStr(valueStr);
        skipSpaces(bracketStr, iw);
        return value;
    }

    private SimpleTreeNode fromBracketStr(String bracketStr, IndexWrapper iw) throws Exception {
        T parentValue = readValue(bracketStr, iw);
        SimpleTreeNode parentNode = new SimpleTreeNode(parentValue);
        if (bracketStr.charAt(iw.index) == '(') {
            iw.index++;
            skipSpaces(bracketStr, iw);
            if (bracketStr.charAt(iw.index) != ',') {
                parentNode.left = fromBracketStr(bracketStr, iw);
                skipSpaces(bracketStr, iw);
            }
            if (bracketStr.charAt(iw.index) == ',') {
                iw.index++;
                skipSpaces(bracketStr, iw);
            }
            if (bracketStr.charAt(iw.index) != ')') {
                parentNode.right = fromBracketStr(bracketStr, iw);
                skipSpaces(bracketStr, iw);
            }
            if (bracketStr.charAt(iw.index) != ')') {
                throw new Exception(String.format("Ожидалось ')' [%d]", iw.index));
            }
            iw.index++;
        }

        return parentNode;
    }

    public void fromBracketNotation(String bracketStr) throws Exception {
        IndexWrapper iw = new IndexWrapper();
        SimpleTreeNode root = fromBracketStr(bracketStr, iw);
        if (iw.index < bracketStr.length()) {
            throw new Exception(String.format("Ожидался конец строки [%d]", iw.index));
        }
        this.root = root;
    }

    public void clearOnes(){
        if(this.root == null) return;

        class NodeAndLevel {
            public SimpleTreeNode node;
            public int level;

            public NodeAndLevel(SimpleTreeNode node, int level) {
                this.node = node;
                this.level = level;
            }
        }

            Stack<NodeAndLevel> stack = new Stack<>();

        if (root.right != null){
            stack.push(new NodeAndLevel(root, 0));
            stack.push(new NodeAndLevel(root.right, 1));
        }
        if (root.left != null) {
            stack.push(new NodeAndLevel(root, 0));
            stack.push(new NodeAndLevel(root.left,   1));
        }

        while (!stack.isEmpty()) {
            NodeAndLevel tmp = stack.pop();
            SimpleTreeNode node = tmp.node;
            int level = tmp.level;

            tmp = stack.pop();
            SimpleTreeNode lastNode = tmp.node;

            // Основная часть
            if(node.right == null && node.left != null || node.right != null && node.left == null ){
                System.out.println("Удаление элемента, со значением: " + node.value);
                if(node.left != null){
                    if (lastNode.left == node){
                        lastNode.left = node.left;
                    }
                    else{
                        lastNode.right = node.left;
                    }
                }
                else{
                    if (lastNode.left == node){
                        lastNode.left = node.right;
                    }
                    else{
                        lastNode.right = node.right;
                    }
                }
            }



            if (node.right != null){
                stack.push(new NodeAndLevel(node, level));
                stack.push(new NodeAndLevel(node.right, level + 1));
            }
            if (node.left != null) {
                stack.push(new NodeAndLevel(node, level));
                stack.push(new NodeAndLevel(node.left, level + 1));
            }
        }
    }
}
