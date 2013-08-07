package fas.algorithms.sifting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import fas.algorithms.eades.LinearArrangementAlgorithm;

public class KobilanskiChanasAlgorithm implements LinearArrangementAlgorithm {
    @Override
    public List<Integer> linearArrangement(DirectedGraph<Integer, DefaultEdge> graph) {
        return linearArrangement(graph, graph.vertexSet());
    }

    public List<Integer> linearArrangement(DirectedGraph<Integer, DefaultEdge> graph, Iterable<Integer> initialOrder) {
        return linearArrangement(new SiftingGraphWrapper(graph), initialOrder);
    }

    public List<Integer> linearArrangement(SiftingGraphWrapper graph, Iterable<Integer> initialOrder) {
        MultipleSiftingAlgorithm multipleSifting = new MultipleSiftingAlgorithm();

        LinearArrangement newArr = multipleSifting.linearArrangement(graph, initialOrder);
        LinearArrangement oldArr = null;
        do {
            oldArr = newArr;
            List<Integer> arrayList = new ArrayList<>(oldArr.asList());
            Collections.reverse(arrayList);
            newArr = multipleSifting.linearArrangement(graph, arrayList);
        } while (oldArr.score() != newArr.score());
        return newArr.asList();
    }

    @Override
    public String toString() {
        return "Kobylanski-Chanas";
    }

}
