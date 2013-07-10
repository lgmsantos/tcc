package fas.algorithms.saab;

import java.util.List;
import java.util.Set;

import org.jgrapht.graph.DefaultEdge;


public interface SaabGraph {

    Set<Integer> vertexSet();

    Set<DefaultEdge> edgeSet();
    
    List<SaabGraph> components();

    List<Integer> targetsOf(Integer v);

    List<Integer> sourcesOf(Integer v);

    SaabGraph subgraph(Set<Integer> set);

    boolean containsEdge(Integer s, Integer t);

    DefaultEdge getEdge(Integer s, Integer t);

    Integer getEdgeSource(DefaultEdge e);

    Integer getEdgeTarget(DefaultEdge e);


}
