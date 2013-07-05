package graph.algorithms.task;

import java.util.Date;
import java.util.Random;
import java.util.Set;

import org.jgrapht.graph.DefaultEdge;

import fas.algorithms.saab.SaabAlgorithm;
import graph.algorithms.task.execution.FASResult;

public class SaabTask implements Task<GraphInput>{

    private SaabAlgorithm algorithm;

    public SaabTask() {
        algorithm = new SaabAlgorithm(new Random());
    }   
    
    @Override
    public int iterationCount() {
        return 50;
    }

    @Override
    public FASResult execute(GraphInput input) {
        long start = new Date().getTime();
        Set<DefaultEdge> arcSet = algorithm.feedbackArcSet(input.getGraph());
        long elapsed = new Date().getTime() - start;
        return new FASResult(toString(), input.vertexSetSize(), input.edgeSetSize(), elapsed, arcSet.size());
    }
    
    @Override
    public String toString() {
        return "Saab";
    }

}
