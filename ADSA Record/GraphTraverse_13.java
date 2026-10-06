public class GraphTraverse_13 {

    static int[][] graph = {
            { 0, 1, 1, 0, 0 },
            { 1, 0, 0, 1, 0 },
            { 1, 0, 0, 1, 1 },
            { 0, 1, 1, 0, 1 },
            { 0, 0, 1, 1, 0 }
    };

    static int n = graph.length;

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

        java.util.Queue<Integer> q = new java.util.ArrayDeque<>();

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

        boolean[] visited = new boolean[n];

        System.out.println("DFS Traversal:");
        DFS(0, visited);

        System.out.println("\nBFS Traversal:");
        BFS(0);
    }
}
