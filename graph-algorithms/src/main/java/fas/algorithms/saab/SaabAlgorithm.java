package fas.algorithms.saab;

import static java.util.Collections.emptySet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.StrongConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedSubgraph;

public class SaabAlgorithm implements ArcSetAlgorithm {

    private Random random;

    public SaabAlgorithm(Random random) {
        this.random = random;
    }

    @Override
    public Set<DefaultEdge> feedbackArcSet(DirectedGraph<Integer, DefaultEdge> graph) {
        if (graph.vertexSet().size() == 1)
            return emptySet();

        StrongConnectivityInspector<Integer, DefaultEdge> inspector = new StrongConnectivityInspector<Integer, DefaultEdge>(
                graph);

        List<DirectedSubgraph<Integer, DefaultEdge>> components = inspector.stronglyConnectedSubgraphs();

        Set<DefaultEdge> result = new HashSet<>();
        if (components.size() == 1) {
            for (Set<Integer> sub : bisect(components.remove(0)))
                components.add(new DirectedSubgraph<>(graph, sub, null));

            result.addAll(fas(graph, components.get(0), components.get(1)));
        }

        for (DirectedSubgraph<Integer, DefaultEdge> sub : components)
            result.addAll(feedbackArcSet(sub));

        return result;
    }

    private Collection<? extends DefaultEdge> fas(DirectedGraph<Integer, DefaultEdge> graph,
            DirectedSubgraph<Integer, DefaultEdge> sub1, DirectedSubgraph<Integer, DefaultEdge> sub2) {
        Set<DefaultEdge> arcSet = new HashSet<>();
        for (Integer v1 : sub1.vertexSet())
            for (Integer v2 : sub2.vertexSet())
                if (graph.containsEdge(v2, v1))
                    arcSet.add(graph.getEdge(v2, v1));
        return arcSet;
    }

    private List<Set<Integer>> bisect(DirectedGraph<Integer, DefaultEdge> graph) {
        return new StochasticEvolutionBisect(random, graph).bisect();
    }
}
