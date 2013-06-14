package fas.algorithms.eades;

import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public interface LinearArrangementAlgorithm {

    List<Integer> linearArrangement(DirectedGraph<Integer, DefaultEdge> graph);

}
