public class BellmanFord_15 {

    public static void main(String[] args) {

        int vertices = 5;

        // {source, destination, weight}
        int[][] edges = {
                { 0, 1, 4 },
                { 0, 2, 2 },
                { 1, 2, 3 },
                { 1, 3, 2 },
                { 1, 4, 3 },
                { 2, 1, 1 },
                { 2, 3, 4 },
                { 2, 4, 5 },
                { 4, 3, -5 }
        };

        int[] distance = new int[vertices];

        for (int i = 0; i < vertices; i++)
            distance[i] = Integer.MAX_VALUE;

        distance[0] = 0;

        // Relax all edges V-1 times
        for (int i = 1; i < vertices; i++) {

            for (int j = 0; j < edges.length; j++) {

                int u = edges[j][0];
                int v = edges[j][1];
                int weight = edges[j][2];

                if (distance[u] != Integer.MAX_VALUE &&
                        distance[u] + weight < distance[v]) {

                    distance[v] = distance[u] + weight;
                }
            }
        }

        // Check for negative weight cycle
        for (int j = 0; j < edges.length; j++) {

            int u = edges[j][0];
            int v = edges[j][1];
            int weight = edges[j][2];

            if (distance[u] != Integer.MAX_VALUE &&
                    distance[u] + weight < distance[v]) {

                System.out.println("Graph contains a negative weight cycle.");
                return;
            }
        }

        System.out.println("Shortest distances from vertex 0:");

        for (int i = 0; i < vertices; i++) {
            System.out.println("0 -> " + i + " = " + distance[i]);
        }
    }
}
