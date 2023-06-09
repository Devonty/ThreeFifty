package task_7_1;

public class GraphValidator {
    public static boolean isConnected(boolean[][] graph, boolean[] visited, int node) {
        visited[node] = true;
        for (int i = 0; i < graph.length; i++) {
            if (graph[node][i] && !visited[i]) {
                if (isConnected(graph, visited, i)) {
                    return true;
                }
            }
        }
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

}
