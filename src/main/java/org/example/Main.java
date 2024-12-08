package org.example;

import java.util.Random;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        int scoutBees = 5;
        int foragerBees = 15;
        int iterations = 100;
        int numAreas = 5;

        int vertices = 300;
        Graph graph = new Graph(vertices);

        Random random = new Random();
        for (int i = 0; i < vertices; i++) {
            for (int j = i + 1; j < vertices; j++) {
                if (random.nextInt(100) < 30) {
                    graph.addEdge(i, j);
                }
            }
        }

        BeeAlgorithm algorithm = new BeeAlgorithm(graph, scoutBees, foragerBees, iterations, numAreas);
        Set<Integer> maxClique = algorithm.findMaxClique();

        System.out.printf("Max Clique Size: %d\n", maxClique.size());
        System.out.println("Max Clique: " + maxClique);
    }
}