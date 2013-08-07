package fas.algorithms.scattersearch.fas;

import static main.FASUtils.feedbackArcSetFromLinearArrangement;


import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import fas.algorithms.scattersearch.EvaluationStrategy;


public class NodeSequenceEvaluator implements EvaluationStrategy<Sequence> {

    private DirectedGraph<Integer, DefaultEdge> graph;

    public NodeSequenceEvaluator(DirectedGraph<Integer, DefaultEdge> graph) {
        this.graph = graph;        
    }
    
    @Override
    public int compare(Sequence s1, Sequence s2) {
        return Integer.compare(eval(s1), eval(s2));
    }

    @Override
    public int eval(Sequence sequence) {
        return feedbackArcSetFromLinearArrangement(graph, sequence.asList()).size();
    }
}
