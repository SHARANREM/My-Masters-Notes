
import java.util.*;

public class GraphTraverse {

    static int[][] graph;
    static int n;

    static void inputGraph() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        n = sc.nextInt();

        graph = new int[n][n];

        System.out.println("Enter adjacency matrix:");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
    }

    static void DFS(int v, boolean[] visited) {

        System.out.print(v + " ");
        visited[v] = true;

        for (int i = 0; i < n; i++) {
            if (graph[v][i] == 1 && !visited[i])
                DFS(i, visited);
        }
    }

    static void BFS(int start) {

        boolean[] visited = new boolean[n];

        java.util.Queue<Integer> q = new java.util.ArrayDeque<Integer>();

        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {

            int v = q.remove();

            System.out.print(v + " ");

            for (int i = 0; i < n; i++) {

                if (graph[v][i] == 1 && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    public static void main(String[] args) {

        inputGraph();

        boolean[] visited = new boolean[n];

        System.out.println("DFS Traversal:");
        DFS(0, visited);

        System.out.println("\nBFS Traversal:");
        BFS(0);
    }
}
