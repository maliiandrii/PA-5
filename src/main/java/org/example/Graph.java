package org.example;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int vertices;
    private List<Integer>[] adjacencyList;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int v, int w) {
        adjacencyList[v].add(w);
        adjacencyList[w].add(v);
    }

    public List<Integer> getNeighbors(int v) {
        return adjacencyList[v];
    }

    public int getVertices() {
        return vertices;
    }
}
