public class BellmanFord15 {

    public static void main(String[] args) {

        int vertices = 5;

        // {source, destination, weight}
        int[][] edges = {
            {0, 1, 4},
            {0, 2, 2},
            {1, 2, 3},
            {1, 3, 2},
            {1, 4, 3},
            {2, 1, 1},
            {2, 3, 4},
            {2, 4, 5},
            {4, 3, -5}
        };

        int[] distance = new int[vertices];

        // Set distance to infinity
        for (int i = 0; i < vertices; i++)
            distance[i] = 999;

        // Starting vertex is 0
        distance[0] = 0;

        // Bellman-Ford algorithm
        for (int i = 1; i < vertices; i++) {

            for (int j = 0; j < edges.length; j++) {

                int u = edges[j][0];
                int v = edges[j][1];
                int weight = edges[j][2];

                if (distance[u] != 999 &&
                    distance[u] + weight < distance[v]) {

                    distance[v] = distance[u] + weight;
                }
            }
        }

        // Display shortest distances
        System.out.println("Shortest distances from vertex 0:");

        for (int i = 0; i < vertices; i++) {
            System.out.println("0 -> " + i + " = " + distance[i]);
        }
    }
}
