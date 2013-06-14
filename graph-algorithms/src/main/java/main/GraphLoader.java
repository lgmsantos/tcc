package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public class GraphLoader {

    public DirectedGraph<Integer, DefaultEdge> loadDirectedGraph(File graphFile) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(graphFile);
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            SimpleDirectedGraph<Integer, DefaultEdge> graph = new SimpleDirectedGraph<>(DefaultEdge.class);
            while (n > 0)
                graph.addVertex(n--);
            while (m-- > 0)
                graph.addEdge(scanner.nextInt(), scanner.nextInt());
            return graph;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try { scanner.close(); } catch (Exception e) { }
        }
    }
}
