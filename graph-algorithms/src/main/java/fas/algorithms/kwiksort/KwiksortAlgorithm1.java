package fas.algorithms.kwiksort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import fas.algorithms.eades.LinearArrangementAlgorithm;

public class KwiksortAlgorithm1 implements LinearArrangementAlgorithm{

    private Random random;

    public KwiksortAlgorithm1(Random random){
        this.random = random;        
    }
    
    public List<Integer> linearArrangement(DirectedGraph<Integer, DefaultEdge> graph) {
        return linearArrangement(graph, graph.vertexSet());
    }

    private List<Integer> linearArrangement(DirectedGraph<Integer, DefaultEdge> graph, Set<Integer> subSet) {
        if(subSet.size() <= 1)
            return new ArrayList<>(subSet);
        
        HashSet<Integer> mid = new HashSet<>(subSet);
        Integer pivot = randomElement(mid);

        Set<Integer> pre = new HashSet<>();
        Set<Integer> pos = new HashSet<>();

        for (DefaultEdge e : graph.incomingEdgesOf(pivot)) {
            Integer source = graph.getEdgeSource(e);
            if(mid.contains(source))
                pre.add(source);
        }

        for (DefaultEdge e : graph.outgoingEdgesOf(pivot)){
            Integer target = graph.getEdgeTarget(e);
            if(mid.contains(target))
                pos.add(target);
        }
        
        if(pre.isEmpty() && pos.isEmpty())
            return new ArrayList<>(mid);

        mid.removeAll(pre);
        mid.removeAll(pos);

        List<Integer> result = new ArrayList<>(linearArrangement(graph, pre));
        result.addAll(linearArrangement(graph, mid));
        result.addAll(linearArrangement(graph, pos));
        
        return result;
    }

    private Integer randomElement(Set<Integer> vertexSet) {
        int r = random.nextInt(vertexSet.size());
        Integer pivot = null;
        for (Integer v : vertexSet)
            if (r-- == 0) {
                pivot = v;
                break;
            }
        return pivot;
    }

}
