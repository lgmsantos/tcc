package fas.algorithms.sifting;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class SiftingGraphWrapper {

    private DirectedGraph<Integer, DefaultEdge> graph;
    private boolean[][] edgeMatrix;

    public SiftingGraphWrapper(DirectedGraph<Integer, DefaultEdge> graph) {
        this.graph = graph;
        edgeMatrix = new boolean[1001][1001];
        for(DefaultEdge e: graph.edgeSet()) {
            Integer s = graph.getEdgeSource(e);
            Integer t = graph.getEdgeTarget(e);
            edgeMatrix[s][t] = true;
        }
    }

    public boolean containsEdge(Integer source, Integer target) {
//        return graph.containsEdge(source, target);
        return edgeMatrix[source][target];
    }
    
    public DirectedGraph<Integer, DefaultEdge> baseGraph(){
        return graph;
    }

    public Iterable<Integer> vertexSet() {
        return graph.vertexSet();
    }
    
}
