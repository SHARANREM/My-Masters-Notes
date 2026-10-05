public class Prim14 {

    public static void main(String[] args) {

        int[][] graph = {
            {0, 2, 0, 6, 0},
            {2, 0, 3, 8, 5},
            {0, 3, 0, 0, 7},
            {6, 8, 0, 0, 9},
            {0, 5, 7, 9, 0}
        };

        int n = 5;
        int[] selected = new int[n];

        selected[0] = 1;

        int edges = 0;
        int total = 0;

        System.out.println("Edges in Minimum Spanning Tree:");

        while (edges < n - 1) {

            int min = 999;
            int x = 0, y = 0;

            for (int i = 0; i < n; i++) {
                if (selected[i] == 1) {

                    for (int j = 0; j < n; j++) {

                        if (selected[j] == 0 && graph[i][j] != 0) {

                            if (graph[i][j] < min) {
                                min = graph[i][j];
                                x = i;
                                y = j;
                            }
                        }
                    }
                }
            }

            System.out.println(x + " - " + y + " : " + min);

            total = total + min;
            selected[y] = 1;
            edges++;
        }

        System.out.println("Minimum cost = " + total);
    }
}