package main;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.parameters.ComponentParameter;
import org.picocontainer.parameters.ConstantParameter;

import fas.algorithms.scattersearch.CombinationStrategy;
import fas.algorithms.scattersearch.EvaluationStrategy;
import fas.algorithms.scattersearch.LocalSearchStrategy;
import fas.algorithms.scattersearch.RefSet;
import fas.algorithms.scattersearch.ScatterSearchProcedure;
import fas.algorithms.scattersearch.SubSet;
import fas.algorithms.scattersearch.fas.GloverPermutation;
import fas.algorithms.scattersearch.fas.NodeSequenceEvaluator;
import fas.algorithms.scattersearch.fas.RefSetImpl;
import fas.algorithms.scattersearch.fas.Sequence;

public class ScatterMain {
    public static void main(String[] args) {
        MutablePicoContainer container = new DefaultPicoContainer();
        DirectedGraph<Integer, DefaultEdge> graph = Main.loadGraph("P50-200.dat");
        container.addComponent(graph);
        container.addComponent(NodeSequenceEvaluator.class);
        container.addComponent(RefSet.class, RefSetImpl.class, new ConstantParameter(10), new ComponentParameter(
                EvaluationStrategy.class));
        container.addComponent(ScatterSearchProcedure.class);
        container.addComponent(GloverPermutation.class);
        container.addComponent(combinationStrategy());
        container.addComponent(localSearch());
        @SuppressWarnings("unchecked")
        ScatterSearchProcedure<Sequence> scatterSearch = container.getComponent(ScatterSearchProcedure.class);
        scatterSearch.search(new Sequence(new ArrayList<Integer>()));
    }

    private static LocalSearchStrategy<List<Integer>> localSearch() {
        return new LocalSearchStrategy<List<Integer>>() {

            @Override
            public List<Integer> improve(List<Integer> solution) {
                return emptyList();
            }
        };
    }

    private static CombinationStrategy<Sequence, List<Sequence>> combinationStrategy() {
        return new CombinationStrategy<Sequence, List<Sequence>>() {

            @Override
            public List<Sequence> combine(SubSet<Sequence> subSet) {
                return emptyList();
            }

        };
    }
}
