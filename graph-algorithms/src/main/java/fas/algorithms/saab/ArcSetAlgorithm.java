package fas.algorithms.saab;

import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public interface ArcSetAlgorithm {
    Set<DefaultEdge> feedbackArcSet(DirectedGraph<Integer, DefaultEdge> graph);
}
