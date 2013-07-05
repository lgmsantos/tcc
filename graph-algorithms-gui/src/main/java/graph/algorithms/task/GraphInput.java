package graph.algorithms.task;

import static java.lang.String.format;

import java.io.File;
import java.io.Serializable;

import main.GraphLoader;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class GraphInput implements TaskInput<GraphInput>, Serializable {

    private static final long serialVersionUID = 1L;
    
    private DirectedGraph<Integer, DefaultEdge> graph;

    public GraphInput(File file) {
        graph = new GraphLoader().loadDirectedGraph(file);            
    }

    @Override
    public String toString() {
        return format("G(%s, %s)", vertexSetSize(), edgeSetSize());
    }

    @Override
    public int compareTo(GraphInput other) {
        int n = Integer.compare(graph.vertexSet().size(), other.graph.vertexSet().size());
        if(n != 0)
            return n;
        return Integer.compare(graph.edgeSet().size(), other.graph.edgeSet().size());
    }

    public int edgeSetSize() {
        return graph.edgeSet().size();
    }

    public int vertexSetSize() {
        return graph.vertexSet().size();
    }

    public DirectedGraph<Integer, DefaultEdge> getGraph() {
        return graph;
    }
}
