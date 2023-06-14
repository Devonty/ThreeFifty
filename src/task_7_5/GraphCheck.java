package task_7_5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class GraphCheck extends JFrame implements ActionListener {

    private JTextField nodesField, edgesField, BanListField;
    private JTextArea adjacencyListArea;
    private JButton checkButton;
    private JPanel contentPanel;

    public GraphCheck() {
        super("Graph Check");


        // ставим значения по умолчанию
        nodesField.setText("7");
        edgesField.setText("8");

        adjacencyListArea.setText("""
                1 5
                2 5
                2 3
                3 4
                4 5
                4 6
                5 6
                6 7
                """);


        BanListField.setText("1 2");

        nodesField.setSize(200, 80);
        edgesField.setSize(200, 80);
        adjacencyListArea.setSize(200, 160);
        BanListField.setSize(200, 80);
        checkButton.addActionListener(this);


        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(contentPanel, BorderLayout.CENTER);
        contentPane.add(contentPanel, BorderLayout.CENTER);

        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkButton) {
            // Достаем кол-во вершин
            int n = Integer.parseInt(nodesField.getText());
            // Достаем кол-во ребер
            int m = Integer.parseInt(edgesField.getText());
            // Достаем список ребер
            String[] adjacencyList = adjacencyListArea.getText().split("\n");

            boolean[][] graph = new boolean[n][n];
            for (int i = 0; i < m; i++) {
                // Разделяем строку по пробелу на 2 числа
                String[] edge = adjacencyList[i].split(" ");
                int u = Integer.parseInt(edge[0]) - 1; // номер первой вершины
                int v = Integer.parseInt(edge[1]) - 1; // номер второй вершины
                graph[u][v] = true; // соединяем в матрице смежности
                graph[v][u] = true; // соединяем в матрице смежности
            }

            // создаем бан лист
            boolean[] banList = new boolean[n];

            // получаем его из поля ввода
            for(String num : BanListField.getText().split(" ")){
                try {
                    // пытаемся преобразовать в число
                    int index = Integer.parseInt(num) - 1;
                    banList[index] = true;
                } catch (NumberFormatException ex) {
                    // Сломалось - выходим с тем что есть
                    break;
                }
            }

            // Получаем Список циклов
            List<Integer[]> circles = GraphValidator.findCircle(graph, banList);
            // Объявляем строителя строк
            StringBuilder stringBuilder = new StringBuilder();

            // Собираем сообщение на вывод
            for (int i = 0; i < circles.size(); i++) {
                for (int j = 0; j < circles.get(i).length; j++) {
                    circles.get(i)[j]++; // Возращаем нумерацию с 1
                }
                stringBuilder.append(Arrays.toString(circles.get(i))).append('\n');
            }
            // Выводим сообщение
            JOptionPane.showMessageDialog(this, stringBuilder);
        }
    }

    public static void main(String[] args) {
        GraphCheck graphCheck = new GraphCheck();
        graphCheck.setVisible(true);
    }
}