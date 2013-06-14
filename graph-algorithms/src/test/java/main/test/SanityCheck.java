package main.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.jgrapht.alg.StrongConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.junit.Test;

public class SanityCheck {

    @Test
    public void acyclicGraphConnectivity(){
        SimpleDirectedGraph<Integer, DefaultEdge> graph = new SimpleDirectedGraph<>(DefaultEdge.class);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);
        StrongConnectivityInspector<Integer, DefaultEdge> str = new StrongConnectivityInspector<>(graph);
        assertFalse(str.isStronglyConnected());
        assertEquals("[[1], [2]]", str.stronglyConnectedSets().toString());
        assertEquals("[([1], []), ([2], [])]", str.stronglyConnectedSubgraphs().toString());
    }
    
}
