import java.util.*;

public class GraphTravers13 {

    static int[][] graph = {
        {0, 1, 1, 0, 0},
        {1, 0, 0, 1, 0},
        {1, 0, 0, 1, 1},
        {0, 1, 1, 0, 1},
        {0, 0, 1, 1, 0}
    };

    static boolean[] visited = new boolean[5];

    // DFS
    static void DFS(int v) {
        System.out.print(v + " ");
        visited[v] = true;

        for (int i = 0; i < 5; i++) {
            if (graph[v][i] == 1 && !visited[i]) {
                DFS(i);
            }
        }
    }

    // BFS
    static void BFS(int start) {

        boolean[] visited = new boolean[5];

        java.util.Queue<Integer> q = new java.util.LinkedList<>();

        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int v = q.remove();

            System.out.print(v + " ");

            for (int i = 0; i < 5; i++) {
                if (graph[v][i] == 1 && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("DFS Traversal:");
        DFS(0);

        System.out.println("\nBFS Traversal:");
        BFS(0);
    }
}