package main;

import static java.lang.String.valueOf;
import static main.FASUtils.feedbackArcSetFromPartialArrangement;

import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import fas.algorithms.ArcSetAlgorithm;
import fas.algorithms.eades.LinearArrangementAlgorithm;

public class LinearArrangementWrapper implements ArcSetAlgorithm {

    private LinearArrangementAlgorithm algorithm;

    public static LinearArrangementWrapper wrap(LinearArrangementAlgorithm algorithm){
        return new LinearArrangementWrapper(algorithm);
    }
    
    public LinearArrangementWrapper(LinearArrangementAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public Set<DefaultEdge> feedbackArcSet(DirectedGraph<Integer, DefaultEdge> graph) {
        return feedbackArcSetFromPartialArrangement(graph, algorithm.linearArrangement(graph));
    }

    @Override
    public String toString() {
        return valueOf(algorithm);
    }
}
