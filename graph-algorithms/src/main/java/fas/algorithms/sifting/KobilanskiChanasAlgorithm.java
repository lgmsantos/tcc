package fas.algorithms.sifting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import fas.algorithms.eades.LinearArrangementAlgorithm;


public class KobilanskiChanasAlgorithm implements LinearArrangementAlgorithm{

    @Override
    public List<Integer> linearArrangement(DirectedGraph<Integer, DefaultEdge> graph) {
        MultipleSiftingAlgorithm multipleSifting = new MultipleSiftingAlgorithm();
        
        LinearArrangement newArr = multipleSifting.linearArrangement(graph, graph.vertexSet());
        LinearArrangement oldArr = null;
        do{
            oldArr = newArr;
            List<Integer> arrayList = new ArrayList<>(oldArr.asList());
            Collections.reverse(arrayList);
            newArr = multipleSifting.linearArrangement(graph, arrayList);
        }while(oldArr.score() != newArr.score());
        return newArr.asList();
    }

    

}
