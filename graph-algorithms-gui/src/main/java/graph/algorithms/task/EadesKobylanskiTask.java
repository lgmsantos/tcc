package graph.algorithms.task;

import static main.FASUtils.feedbackArcSetFromLinearArrangement;

import java.util.Date;
import java.util.List;
import java.util.Set;


import org.jgrapht.graph.DefaultEdge;

import fas.algorithms.EadesKobylanskiAlgorithmm;
import fas.algorithms.eades.EadesAlgorithm;
import fas.algorithms.eades.LinearArrangementAlgorithm;
import fas.algorithms.sifting.KobilanskiChanasAlgorithm;
import graph.algorithms.task.execution.FASResult;

public class EadesKobylanskiTask implements Task<GraphInput>{

    private LinearArrangementAlgorithm algorithm;

    public EadesKobylanskiTask() {
        algorithm = new EadesKobylanskiAlgorithmm(new KobilanskiChanasAlgorithm(), new EadesAlgorithm());
    }
    
    @Override
    public int iterationCount() {
        return 1;
    }

    @Override
    public FASResult execute(GraphInput input) {
        long start = new Date().getTime();
        List<Integer> arrange = algorithm.linearArrangement(input.getGraph());
        long elapsed = new Date().getTime() - start;
        Set<DefaultEdge> arcSet = feedbackArcSetFromLinearArrangement(input.getGraph(), arrange);
        return new FASResult(toString(), input.vertexSetSize(), input.edgeSetSize(), elapsed, arcSet.size());
    }

    @Override
    public String toString() {
        return "Eades-Kobylanski";
    }    
}
