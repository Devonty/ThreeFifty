package task_7_6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphCheck extends JFrame implements ActionListener {

    private JTextField nodesField, edgesField, fromNodeField;
    private JTextArea adjacencyListArea;
    private JButton checkButton;
    private JPanel contentPanel;
    private JTextField toNodeField;

    public GraphCheck() {
        super("Graph Check");

        nodesField.setText("5");
        edgesField.setText("5");
        fromNodeField.setText("1");
        toNodeField.setText("5");

        adjacencyListArea.setText("""
                1 2
                2 3
                3 4
                4 5
                3 5
                """);



        nodesField.setSize(200, 80);
        edgesField.setSize(200, 80);
        adjacencyListArea.setSize(200, 160);
        fromNodeField.setSize(200, 80);
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
            int n = Integer.parseInt(nodesField.getText());
            int m = Integer.parseInt(edgesField.getText());
            String[] adjacencyList = adjacencyListArea.getText().split("\n");

            boolean[][] graph = new boolean[n][n];
            for (int i = 0; i < m; i++) {
                String[] edge = adjacencyList[i].split(" ");
                int u = Integer.parseInt(edge[0]) - 1;
                int v = Integer.parseInt(edge[1]) - 1;
                graph[u][v] = true;
                graph[v][u] = true;
            }
            int begIndex = Integer.parseInt(fromNodeField.getText()) - 1;
            int endIndex = Integer.parseInt(toNodeField.getText()) - 1;
            GraphValidator.Data data = GraphValidator.inAllPathes(graph, begIndex, endIndex);
            
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < data.visitedInAllPath.length; i++) {
                if(!data.visitedInAllPath[i]) continue;
                stringBuilder.append(i + 1).append(' ');
            }
            
            JOptionPane.showMessageDialog(this, "Nodes in every path: " + stringBuilder);

        }
    }

    public static void main(String[] args) {
        GraphCheck graphCheck = new GraphCheck();
        graphCheck.setVisible(true);
    }
}