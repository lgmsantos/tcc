package fas.algorithms;

import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import fas.algorithms.eades.EadesAlgorithm;
import fas.algorithms.eades.LinearArrangementAlgorithm;
import fas.algorithms.sifting.KobilanskiChanasAlgorithm;

public class EadesKobylanskiAlgorithmm implements LinearArrangementAlgorithm {

    private KobilanskiChanasAlgorithm kc;
    private EadesAlgorithm els;

    public EadesKobylanskiAlgorithmm(KobilanskiChanasAlgorithm kc, EadesAlgorithm els) {
        this.kc = kc;
        this.els = els;
    }

    @Override
    public List<Integer> linearArrangement(DirectedGraph<Integer, DefaultEdge> graph) {
        return kc.linearArrangement(graph, els.linearArrangement(graph));
    }

    @Override
    public String toString() {
        return "Eades-Kobylanski";
    }
}
