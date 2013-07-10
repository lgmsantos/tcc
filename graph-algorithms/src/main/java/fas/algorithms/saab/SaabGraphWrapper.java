package fas.algorithms.saab;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.StrongConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedSubgraph;

class SaabGraphWrapper implements SaabGraph {

    private DirectedGraph<Integer, DefaultEdge> graph;
    private Map<Integer, List<Integer>> sourceMap;
    private Map<Integer, List<Integer>> targetMap;

    public SaabGraphWrapper(DirectedGraph<Integer, DefaultEdge> graph) {
        this.graph = graph;
    }

    @Override
    public Set<Integer> vertexSet() {
        return graph.vertexSet();
    }

    @Override
    public List<SaabGraph> components() {
        StrongConnectivityInspector<Integer, DefaultEdge> inspector = new StrongConnectivityInspector<>(graph);
        if (inspector.isStronglyConnected())
            return asList((SaabGraph) this);
        List<SaabGraph> graphs = new ArrayList<>();
        for (Set<Integer> comp : inspector.stronglyConnectedSets())
            graphs.add(subgraph(comp));
        return graphs;
    }

    @Override
    public List<Integer> targetsOf(Integer v) {
        if (targetMap == null)
            initializeMaps();
        return targetMap.get(v);
    }

    @Override
    public List<Integer> sourcesOf(Integer v) {
        if (sourceMap == null)
            initializeMaps();
        return sourceMap.get(v);
    }

    private void initializeMaps() {
        targetMap = new HashMap<>();
        sourceMap = new HashMap<>();
        for(Integer v: vertexSet()){
            sourceMap.put(v, new ArrayList<Integer>());
            targetMap.put(v, new ArrayList<Integer>());
        }
        for(DefaultEdge e: graph.edgeSet()){
            Integer source = graph.getEdgeSource(e);
            Integer target = graph.getEdgeTarget(e);
            sourceMap.get(target).add(source);
            targetMap.get(source).add(target);
        }
    }

    @Override
    public SaabGraph subgraph(Set<Integer> set) {
        return new SaabGraphWrapper(new DirectedSubgraph<>(graph, set, null));
    }

    @Override
    public boolean containsEdge(Integer s, Integer t) {
        return graph.containsEdge(s, t);
    }

    @Override
    public DefaultEdge getEdge(Integer s, Integer t) {
        return graph.getEdge(s, t);
    }

    @Override
    public Set<DefaultEdge> edgeSet() {
        return graph.edgeSet();
    }

    @Override
    public Integer getEdgeSource(DefaultEdge e) {
        return graph.getEdgeSource(e);
    }

    @Override
    public Integer getEdgeTarget(DefaultEdge e) {
        return graph.getEdgeTarget(e);
    }

}
