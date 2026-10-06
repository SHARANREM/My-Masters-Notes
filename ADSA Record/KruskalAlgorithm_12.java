import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public int compareTo(Edge e) {
        return Integer.compare(this.weight, e.weight);
    }
}

class DisjointSet {
    Map<Integer, Integer> parent = new HashMap<>();
    Map<Integer, Integer> rank = new HashMap<>();

    void makeSet(Set<Integer> vertices) {
        for (int v : vertices) {
            parent.put(v, v);
            rank.put(v, 0);
        }
    }

    int find(int v) {
        if (parent.get(v) != v)
            parent.put(v, find(parent.get(v)));

        return parent.get(v);
    }

    void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB)
            return;

        if (rank.get(rootA) < rank.get(rootB)) {
            parent.put(rootA, rootB);
        } else if (rank.get(rootA) > rank.get(rootB)) {
            parent.put(rootB, rootA);
        } else {
            parent.put(rootB, rootA);
            rank.put(rootA, rank.get(rootA) + 1);
        }
    }
}

public class KruskalAlgorithm_12 {

    public static void main(String[] args) {

        String fileName = "data.txt";

        Set<Integer> vertices = new HashSet<>();
        List<Edge> edges = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = br.readLine()) != null) {

                line = line.trim();

                if (line.isEmpty())
                    continue;

                String[] parts = line.split("\\s+");

                if (parts.length == 1) {

                    int vertex = Integer.parseInt(parts[0]);
                    vertices.add(vertex);

                } else if (parts.length == 3) {

                    int src = Integer.parseInt(parts[0]);
                    int dest = Integer.parseInt(parts[1]);
                    int weight = Integer.parseInt(parts[2]);

                    edges.add(new Edge(src, dest, weight));

                } else {
                    System.out.println("Invalid line: " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading data.txt");
            return;
        }

        DisjointSet ds = new DisjointSet();
        ds.makeSet(vertices);

        Collections.sort(edges);

        List<Edge> mst = new ArrayList<>();
        int totalWeight = 0;

        for (Edge edge : edges) {

            int rootSrc = ds.find(edge.src);
            int rootDest = ds.find(edge.dest);

            if (rootSrc != rootDest) {

                mst.add(edge);
                totalWeight += edge.weight;

                ds.union(edge.src, edge.dest);
            }

            if (mst.size() == vertices.size() - 1)
                break;
        }

        System.out.println("Edges in Minimum Spanning Tree:");

        for (Edge edge : mst) {
            System.out.println(
                    edge.src + " - " + edge.dest +
                            " : " + edge.weight);
        }

        if (mst.size() == vertices.size() - 1) {
            System.out.println("Total Weight: " + totalWeight);
        } else {
            System.out.println("Graph is disconnected. MST cannot be formed.");
        }
    }
}
