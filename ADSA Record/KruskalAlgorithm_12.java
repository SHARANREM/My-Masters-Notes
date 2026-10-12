/*
data.txt content
1
2
3
4
5
1 2 10
1 3 6
1 4 5
2 3 15
3 4 4
4 5 7
2 5 8

*/

import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;
    Edge(int s, int d, int w) { src = s; dest = d; weight = w; }
    public int compareTo(Edge e) { return Integer.compare(weight, e.weight); }
}

class DisjointSet {
    Map<Integer, Integer> parent = new HashMap<>(), rank = new HashMap<>();

    void makeSet(Set<Integer> v) {
        for (int x : v) { parent.put(x, x); rank.put(x, 0); }
    }

    int find(int x) {
        if (parent.get(x) != x) parent.put(x, find(parent.get(x)));
        return parent.get(x);
    }

    void union(int a, int b) {
        int x = find(a), y = find(b);
        if (x == y) return;
        if (rank.get(x) < rank.get(y)) parent.put(x, y);
        else {
            parent.put(y, x);
            if (rank.get(x).equals(rank.get(y))) rank.put(x, rank.get(x) + 1);
        }
    }
}

public class KruskalAlgorithm_12 {
    public static void main(String[] args) {
        Set<Integer> vertices = new HashSet<>();
        List<Edge> edges = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.trim().split("\\s+");
                if (line.trim().isEmpty()) continue;

                if (p.length == 1) vertices.add(Integer.parseInt(p[0]));
                else if (p.length == 3) {
                    int a = Integer.parseInt(p[0]), b = Integer.parseInt(p[1]);
                    vertices.add(a);
                    vertices.add(b);
                    edges.add(new Edge(a, b, Integer.parseInt(p[2])));
                } else System.out.println("Invalid line: " + line);
            }
        } catch (IOException e) {
            System.out.println("Error reading data.txt");
            return;
        }

        DisjointSet ds = new DisjointSet();
        ds.makeSet(vertices);
        Collections.sort(edges);

        int total = 0, count = 0;
        System.out.println("Edges in Minimum Spanning Tree:");

        for (Edge e : edges) {
            if (ds.find(e.src) != ds.find(e.dest)) {
                ds.union(e.src, e.dest);
                System.out.println(e.src + " - " + e.dest + " : " + e.weight);
                total += e.weight;
                count++;
                if (count == vertices.size() - 1) break;
            }
        }

        if (count == vertices.size() - 1)
            System.out.println("Total Weight: " + total);
        else
            System.out.println("Graph is disconnected. MST cannot be formed.");
    }
}
