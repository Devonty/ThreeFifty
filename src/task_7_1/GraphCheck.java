package task_7_1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GraphCheck extends JFrame implements ActionListener {

    private JTextField nodesField, edgesField, fromNode;
    private JTextArea adjacencyListArea;
    private JButton checkButton;
    private JPanel contentPanel;

    public GraphCheck() {
        super("Graph Check");

        nodesField.setText("6");
        edgesField.setText("4");
        adjacencyListArea.setText("""
                1 2
                2 6
                3 4
                4 5
                """);


        fromNode.setText("1");

        nodesField.setSize(200, 80);
        edgesField.setSize(200, 80);
        adjacencyListArea.setSize(200, 160);
        fromNode.setSize(200, 80);
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

            boolean[] visited = new boolean[n];

            if (GraphValidator.isConnected(graph, visited, Integer.parseInt(fromNode.getText()) - 1)) {
                JOptionPane.showMessageDialog(this, "The graph is connected.");
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    if (visited[i]) continue;
                    stringBuilder.append(i + 1).append(' ');
                }
                JOptionPane.showMessageDialog(this, "The graph is not connected to: " + stringBuilder.toString());
            }
        }
    }

    public static void main(String[] args) {
        GraphCheck graphCheck = new GraphCheck();
        graphCheck.setVisible(true);
    }
}