package task_7_6;

public class GraphValidator {

    public static class Data {
        int begIndex;
        int endIndex;
        boolean[] visitedInAllPath;

        public Data(int begNode, int endNode, int nodeCount) {
            this.begIndex = begNode;
            this.endIndex = endNode;

            this.visitedInAllPath = new boolean[nodeCount];
            for (int i = 0; i < nodeCount; i++) {
                this.visitedInAllPath[i] = true;
            }
        }

        public void merge(boolean[] visited) {
            if (visitedInAllPath.length != visited.length)
                throw new ArrayIndexOutOfBoundsException("Different lengths");

            for (int i = 0; i < visited.length; i++) {
                visitedInAllPath[i] = visitedInAllPath[i] && visited[i];
            }
        }
    }

    private static void inAllPathes(boolean[][] graph, boolean[] visited, int node, Data data) {
        visited[node] = true;
        if(node == data.endIndex){
            data.merge(visited);

            visited[node] = false;
            return;
        }

        for (int i = 0; i < graph.length; i++) {
            if (graph[node][i] && !visited[i]) {
                inAllPathes(graph, visited, i, data);
            }
        }

        visited[node] = false;
        return;
    }

    public static Data inAllPathes(boolean[][] graph, int begIndex, int endIndex) {
        Data data = new Data(begIndex, endIndex, graph.length);
        inAllPathes(graph, new boolean[graph.length], begIndex, data);
        return data;
    }

}
