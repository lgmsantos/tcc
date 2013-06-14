package fas.algorithms.eades;

import static java.util.Collections.max;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

class EadesGraphWrapper {
    private DirectedGraph<Integer, DefaultEdge> graph;
    private Set<Integer> vertexSet;
    private int[] inDegrees;
    private int[] outDegrees;

    public EadesGraphWrapper(DirectedGraph<Integer, DefaultEdge> graph){
        initialize(graph);
    }
    
    public boolean isEmpty(){
        return vertexSet.isEmpty();
    }

    public Integer nextSink(){
        return nextWithZeroDegree(outDegrees);
    }

    public Integer nextSource(){
        return nextWithZeroDegree(inDegrees);
    }

    public Integer nextMaxDeltaVertex(){
        if(vertexSet.isEmpty())
            return null;
        
        return max(vertexSet, new Comparator<Integer>(){
            @Override
            public int compare(Integer v1, Integer v2) {
                return deltaDegree(v1) - deltaDegree(v2);
            }
        });
    }

    public void remove(Integer vertex){
        vertexSet.remove(vertex);
        
        for(DefaultEdge e: graph.outgoingEdgesOf(vertex)) {
            Integer v = graph.getEdgeTarget(e);
            if(vertexSet.contains(v))
                inDegrees[v]--;
        }
        
        for(DefaultEdge e: graph.incomingEdgesOf(vertex)){
            Integer v = graph.getEdgeSource(e);
            if(vertexSet.contains(v))
                outDegrees[v]--;
        }
    }

    private void initialize(DirectedGraph<Integer, DefaultEdge> graph) {
        this.graph = graph;
        vertexSet = new TreeSet<>(graph.vertexSet());
        int size = max(vertexSet);

        inDegrees = new int[size + 1];
        outDegrees = new int[size + 1];
        for(Integer v: vertexSet){
            inDegrees[v] = graph.inDegreeOf(v);
            outDegrees[v] = graph.outDegreeOf(v);
        }
    }
    
    private int deltaDegree(Integer v) {
        return outDegrees[v] - inDegrees[v];
    }            

    private Integer nextWithZeroDegree(int[] degrees) {
        for(Integer vertex: vertexSet)
            if(degrees[vertex] == 0)
                return vertex;
        return null;
    }
}
