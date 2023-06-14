package task_7_5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GraphValidator {
    public static class StaticData {
        /*
        * Класс для передачи и сохранения данных при рекурсивном обходе
        * */
        public boolean[][] graph;
        public List<Integer> currPath = new LinkedList<>();
        public List<Integer[]> circles = new LinkedList<>();
        public boolean[] banList;
        public int startNode = 0;

        public StaticData(boolean[][] graph, boolean[] banList) {
            this.graph = graph;
            this.banList = banList;
        }

        public void savePath(){
            // Сохранение пути из currPath в Circles
            // Проверяем на уникальность (т.к. номер стартовой точки перебирается повозрастающе,
            // то если встречаем номер меньше, чем стартовый, то цикл уже сохранен
            for (int i = 0; i < currPath.get(0); i++) {
                if(currPath.contains(i)) return;
            }
            // Сохраняем пройденный путь, который образует цикл
            Integer[] path = currPath.toArray(new Integer[0]);
            circles.add(path);
        }
    }

    private static void findCircle(StaticData data, int node, int lastNode) {
        // Если клетка запрещена, "уходим"
        if (data.banList[node]) return;

        // Приписываем к пути номер текущей вершины
        data.currPath.add(node);
        //System.out.println(data.currPath);
        for (int i = 0; i < data.graph.length; i++) {
            // Если вершина достижима и является стартовой вершиной и не является предыдущей вершиной
            if (data.graph[node][i] && i == data.startNode && data.startNode != lastNode){
                // то значит нашли цикл -> сохраняем путь
                data.savePath();
                //System.out.println("SAVED:" + data.currPath);
            }
            // Если вершина достижима и не в пройденном пути
            if (data.graph[node][i] && !data.currPath.contains(i)) {
                // то посещаем вершину
                findCircle(data, i, node);
            }
        }
        // Выходим из вершины, удаляя ее из пути
        data.currPath.remove(data.currPath.size() - 1);
        return;
    }

    public static List<Integer[]> findCircle(boolean[][] graph, boolean[] banList) {
        // Создаем класс обертку аргументов рекурсии для удобства
        StaticData staticData = new StaticData(graph, banList);
        // Перебираем стартовую вершину
        for (int start = 0; start < graph.length; start++) {
            staticData.startNode = start;
            findCircle(staticData, start, -1);
        }
        // Возращаем собранные данные
        return staticData.circles;
    }
}
