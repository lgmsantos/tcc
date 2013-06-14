package main;

import static java.util.Collections.max;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class FASUtils {

    public static Set<DefaultEdge> feedbackArcSetFromPartialArrangement(DirectedGraph<Integer, DefaultEdge> graph, List<Integer> arrangement){
        int[] positions = new int[max(graph.vertexSet()) + 1];
        for(int i = 0; i < arrangement.size(); i++)
            positions[arrangement.get(i)] = i;

        Set<Integer> set = new HashSet<>(arrangement);
        Set<DefaultEdge> feedbackArcSet = new HashSet<>();
        for(DefaultEdge e: graph.edgeSet()){
            Integer s = graph.getEdgeSource(e);
            Integer t = graph.getEdgeTarget(e);
            if(set.contains(s) && set.contains(t))
                if(positions[t] < positions[s])
                    feedbackArcSet.add(e);
        }
        return feedbackArcSet;        
    }
    
    public static Set<DefaultEdge> feedbackArcSetFromLinearArrangement(DirectedGraph<Integer, DefaultEdge> graph, List<Integer> arrangement){
        int[] positions = new int[arrangement.size() + 1];
        for(int i = 0; i < arrangement.size(); i++)
            positions[arrangement.get(i)] = i;

        Set<DefaultEdge> feedbackArcSet = new HashSet<>();
        for(DefaultEdge e: graph.edgeSet()){
            Integer s = graph.getEdgeSource(e);
            Integer t = graph.getEdgeTarget(e);
            if(positions[t] < positions[s])
                feedbackArcSet.add(e);
        }
        return feedbackArcSet;
    }
    
}
