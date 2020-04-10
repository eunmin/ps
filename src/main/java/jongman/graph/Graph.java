package jongman.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph {
    int size;
    List<Boolean> visited = new ArrayList<>();
    List<List<Integer>> adj = new ArrayList<>();
    List<Integer> order = new ArrayList<>();

    public Graph(int size) {
        this.size = size;

        for (int i = 0; i < size; i++) {
            visited.add(false);
            adj.add(new ArrayList<>());
        }
    }

    public void put(int v1, int v2) {
        adj.get(v1).add(v2);
    }

    public void dfs(int here) {
        System.out.println("visited: " + here);
        visited.set(here, true);

        for (int i = 0; i < adj.get(here).size(); i++) {
            int there = adj.get(here).get(i);
            if (!visited.get(there)) {
                dfs(there);
            }
        }
        order.add(here);
    }

    public int dfsAll() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (!visited.get(i)) {
                count++;
                dfs(i);
            }
        }

        Collections.reverse(order);

        return count;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.put(0, 1);
//        graph.put(0, 9);
        graph.put(1, 2);
        graph.put(1, 5);
//        graph.put(1, 7);
        graph.put(1, 3);
        graph.put(2, 3);
        graph.put(2, 4);
        graph.put(2, 5);
        graph.put(5, 6);
        graph.put(7, 8);
        graph.put(7, 9);

        System.out.println(graph.dfsAll());

        graph.dfs(0);
        System.out.println(graph.visited);

        graph.dfs(9);
        System.out.println(graph.visited);

        Graph dag = new Graph(10);

        dag.put(5, 6);
        dag.put(5, 7);
        dag.put(5, 8);
        dag.put(6, 0);
        dag.put(7, 1);
        dag.put(8, 3);
        dag.put(0, 1);
        dag.put(1, 2);
        dag.put(9, 2);
        dag.put(2, 3);
        dag.put(3, 4);

        System.out.println(dag.dfsAll());
        System.out.println(dag.order);
    }
}
