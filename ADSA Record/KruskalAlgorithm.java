import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// Class representing an Edge in the graph
class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

// Disjoint Set (Union-Find) Data Structure with Path Compression and Union by Rank
class DisjointSet {
    private Map<Integer, Integer> parent = new HashMap<>();
    private Map<Integer, Integer> rank = new HashMap<>();

    public void makeSet(Collection<Integer> vertices) {
        for (int v : vertices) {
            parent.put(v, v);
            rank.put(v, 0);
        }
    }

    public int find(int i) {
        if (!parent.containsKey(i)) return -1;
        if (parent.get(i) != i) {
            parent.put(i, find(parent.get(i))); // Path compression
        }
        return parent.get(i);
    }

    public void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);

        if (rootI != rootJ) {
            int rankI = rank.get(rootI);
            int rankJ = rank.get(rootJ);

            if (rankI < rankJ) {
                parent.put(rootI, rootJ);
            } else if (rankI > rankJ) {
                parent.put(rootJ, rootI);
            } else {
                parent.put(rootJ, rootI);
                rank.put(rootI, rankI + 1);
            }
        }
    }
}

public class KruskalAlgorithm {
    public static void main(String[] args) {
        String fileName = "data.txt"; 
        Set<Integer> vertices = new HashSet<>();
        List<Edge> edges = new ArrayList<>();

        // Read the graph line by line from data.txt
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("Reading graph structure from " + fileName + "...");
            
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                
                if (parts.length == 1) {
                    // Line represents a vertex
                    int v = Integer.parseInt(parts[0]);
                    vertices.add(v);
                } else if (parts.length == 3) {
                    // Line represents an edge: [startVertex, endVertex, weight]
                    int src = Integer.parseInt(parts[0]);
                    int dest = Integer.parseInt(parts[1]);
                    int weight = Integer.parseInt(parts[2]);
                    
                    edges.add(new Edge(src, dest, weight));
                    // Automatically track endpoints as well
                    vertices.add(src);
                    vertices.add(dest);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        // Initialize Disjoint Set
        DisjointSet ds = new DisjointSet();
        ds.makeSet(vertices);

        // Sort all edges in non-decreasing order of their weight
        Collections.sort(edges);

        List<Edge> mst = new ArrayList<>();
        int totalWeight = 0;

        // Kruskal's Algorithm core logic
        for (Edge edge : edges) {
            int rootSrc = ds.find(edge.src);
            int rootDest = ds.find(edge.dest);

            // If including this edge does not cause a cycle
            if (rootSrc != rootDest) {
                mst.add(edge);
                totalWeight += edge.weight;
                ds.union(edge.src, edge.dest); // Pass original endpoints to union
            }
        }

        // Print the results
        System.out.println("\n--- Minimum Spanning Tree (MST) using Kruskal's Algorithm ---");
        System.out.println("Edge \tWeight");
        for (Edge edge : mst) {
            System.out.println(edge.src + " - " + edge.dest + " \t  " + edge.weight);
        }
        System.out.println("Total Weight of MST: " + totalWeight);
    }
}