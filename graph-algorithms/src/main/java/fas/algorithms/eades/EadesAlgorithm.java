package fas.algorithms.eades;

import static java.lang.String.format;
import static java.util.Arrays.asList;

import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class EadesAlgorithm implements LinearArrangementAlgorithm {

    private EadesGraphWrapper graph;
    private Integer[] arrangement;

    public List<Integer> linearArrangement(DirectedGraph<Integer, DefaultEdge> originalGraph) {
        graph = new EadesGraphWrapper(originalGraph);
        arrangement = new Integer[originalGraph.vertexSet().size()];

        try{
            mainLoop();
            return asList(arrangement);
        }finally{
            graph = null;
            arrangement = null;
        }
    }

    private void mainLoop() {
        int sourcePointer = -1;
        int sinkPointer = arrangement.length;
        
        Integer vertex = null;
        
        while (!graph.isEmpty()) {
            while ((vertex = graph.nextSink()) != null)
                insertIntoArrangement(--sinkPointer, vertex);

            while ((vertex = graph.nextSource()) != null)
                insertIntoArrangement(++sourcePointer, vertex);

            if((vertex = graph.nextMaxDeltaVertex()) != null)
                insertIntoArrangement(++sourcePointer, vertex);
        }
        
        if (sourcePointer != sinkPointer - 1)
            throw new RuntimeException(format("%s %s",  sourcePointer, sinkPointer));
    }
    
    private void insertIntoArrangement(int position, Integer vertex) {
        graph.remove(vertex);
        arrangement[position] = vertex;
    }
}
