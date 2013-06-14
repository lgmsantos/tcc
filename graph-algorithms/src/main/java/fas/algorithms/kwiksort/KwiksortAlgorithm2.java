package fas.algorithms.kwiksort;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import fas.algorithms.eades.LinearArrangementAlgorithm;

public class KwiksortAlgorithm2 implements LinearArrangementAlgorithm{

    private Random random;

    public KwiksortAlgorithm2(Random random){
        this.random = random;        
    }
    
    public List<Integer> linearArrangement(DirectedGraph<Integer, DefaultEdge> graph) {
        return linearArrangement(graph, graph.vertexSet());
    }

    private List<Integer> linearArrangement(DirectedGraph<Integer, DefaultEdge> graph, Set<Integer> subSet) {
        if(subSet.isEmpty())
            return emptyList();
        
        HashSet<Integer> vertexSet = new HashSet<>(subSet);
        Integer pivot = randomElement(vertexSet);

        Set<Integer> pre = new HashSet<>();
        Set<Integer> pos = new HashSet<>();

        for (DefaultEdge e : graph.incomingEdgesOf(pivot)) {
            Integer source = graph.getEdgeSource(e);
            if(vertexSet.contains(source))
                pre.add(source);
        }

        for (DefaultEdge e : graph.outgoingEdgesOf(pivot)){
            Integer target = graph.getEdgeTarget(e);
            if(vertexSet.contains(target))
                pos.add(target);
        }

        vertexSet.remove(pivot);
        vertexSet.removeAll(pre);
        vertexSet.removeAll(pos);

        for (Integer vertex : vertexSet)
            (random.nextBoolean() ? pre : pos).add(vertex);

        List<Integer> result = new ArrayList<>(linearArrangement(graph, pre));
        result.add(pivot);
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
