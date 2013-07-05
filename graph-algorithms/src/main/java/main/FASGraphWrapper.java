package main;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.graph.DefaultEdge;

public class FASGraphWrapper {

    private DirectedGraph<Integer, DefaultEdge> graph;

    public FASGraphWrapper(DirectedGraph<Integer, DefaultEdge> graph){
        this.graph = graph;
    }

    public boolean hasCicle(){
        return new CycleDetector<>(graph).detectCycles();
    }

    
}
