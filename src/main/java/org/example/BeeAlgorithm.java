package org.example;

import java.util.*;

public class BeeAlgorithm {
    private Graph graph;
    private int scoutBees;
    private int foragerBees;
    private int iterations;
    private int numAreas;

    public BeeAlgorithm(Graph graph, int scoutBees, int foragerBees, int iterations, int numAreas) {
        this.graph = graph;
        this.scoutBees = scoutBees;
        this.foragerBees = foragerBees;
        this.iterations = iterations;
        this.numAreas = numAreas;
    }

    public Set<Integer> findMaxClique() {
        Set<Integer> bestClique = new HashSet<>();
        Random random = new Random();

        for (int iter = 0; iter < iterations; iter++) {
            List<Set<Integer>> areas = divideGraphIntoAreas();

            for (int i = 0; i < scoutBees; i++) {
                int areaIndex = i % numAreas;
                Set<Integer> area = areas.get(areaIndex);
                Set<Integer> initialClique = expandClique(randomVertexFromArea(area));
                if (initialClique.size() > bestClique.size()) {
                    bestClique = initialClique;
                }

                for (int j = 0; j < foragerBees; j++) {
                    Set<Integer> improvedClique = improveClique(initialClique);
                    if (improvedClique.size() > bestClique.size()) {
                        bestClique = improvedClique;
                    }
                }
            }

            System.out.println("Iteration " + iter + ", best clique so far: " + bestClique + ", size: " + bestClique.size());
        }

        return bestClique;
    }

    private List<Set<Integer>> divideGraphIntoAreas() {
        Random random = new Random();
        List<Set<Integer>> areas = new ArrayList<>();
        for (int i = 0; i < numAreas; i++) {
            areas.add(new HashSet<>());
        }
        for (int vertex = 0; vertex < graph.getVertices(); vertex++) {
            int areaIndex = random.nextInt(numAreas);
            areas.get(areaIndex).add(vertex);
        }
        return areas;
    }

    private int randomVertexFromArea(Set<Integer> area) {
        List<Integer> vertices = new ArrayList<>(area);
        Random random = new Random();
        return vertices.get(random.nextInt(vertices.size()));
    }

    private Set<Integer> expandClique(int startVertex) {
        Set<Integer> clique = new HashSet<>();
        clique.add(startVertex);
        for (int neighbor : graph.getNeighbors(startVertex)) {
            if (isClique(clique, neighbor)) {
                clique.add(neighbor);
            }
        }
        return clique;
    }

    private Set<Integer> improveClique(Set<Integer> clique) {
        Set<Integer> improvedClique = new HashSet<>(clique);
        for (int vertex = 0; vertex < graph.getVertices(); vertex++) {
            if (isClique(improvedClique, vertex)) {
                improvedClique.add(vertex);
            }
        }
        return improvedClique;
    }

    private boolean isClique(Set<Integer> clique, int vertex) {
        for (int member : clique) {
            if (!graph.getNeighbors(member).contains(vertex)) {
                return false;
            }
        }
        return true;
    }
}
