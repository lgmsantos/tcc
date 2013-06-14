package main;

import static java.util.Collections.max;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class FASGraphWrapper {

    private DirectedGraph<Integer, DefaultEdge> graph;
    private Set<Integer> vertexSet;
    private int[] inDegree;
    private int[] outDegree;

    public FASGraphWrapper(DirectedGraph<Integer, DefaultEdge> graph){
        this.graph = graph;
        initialize();
    }

    private void initialize() {
        vertexSet = new HashSet<>(graph.vertexSet());
        inDegree = new int[max(vertexSet) + 1];
        outDegree = new int[max(vertexSet) + 1];
        for(Integer v: vertexSet){
            inDegree[v] = graph.inDegreeOf(v);
            outDegree[v] = graph.outDegreeOf(v);
        }
    }
    
    public Set<Integer> vertexSet(){
        return vertexSet;
    }
    
    public Iterator<Integer> sourceIterator(){
        return new VertexIterator(this) {            
            @Override
            boolean testVertex(Integer vertex) {
                return inDegree[vertex] == 0;
            }
        };
    }
    
    public Iterator<Integer> sinkIterator(){
        return new VertexIterator(this) {            
            @Override
            boolean testVertex(Integer vertex) {
                return outDegree[vertex] == 0;
            }
        };
    }
    
    public boolean hasCicle(){
        try{
            return internalHasCicle();
        }finally{
            initialize();
        }
    }

    private boolean internalHasCicle() {
        Iterator<Integer> iterator = sinkIterator();
        while(iterator.hasNext()){
            iterator.next();
            iterator.remove();
        }
        return !vertexSet.isEmpty();
    }

    public void removeVertex(Integer vertex) {
        vertexSet.remove(vertex);
        
        for(DefaultEdge e: graph.outgoingEdgesOf(vertex)) {
            Integer target = graph.getEdgeTarget(e);
            if(vertexSet.contains(target))
                inDegree[target]--;
        }
        
        for(DefaultEdge e: graph.incomingEdgesOf(vertex)) {
            Integer source = graph.getEdgeSource(e);
            if(vertexSet.contains(source))
                outDegree[source]--;
        }
    }

    public boolean isEmpty() {
        return vertexSet.isEmpty();
    }
    
}
