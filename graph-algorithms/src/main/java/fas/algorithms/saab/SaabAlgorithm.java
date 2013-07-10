package fas.algorithms.saab;

import static java.util.Collections.emptySet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import fas.algorithms.ArcSetAlgorithm;

public class SaabAlgorithm implements ArcSetAlgorithm {

    private StochasticEvolution stochasticEvolution;

    public SaabAlgorithm(StochasticEvolution stochasticEvolution){
        this.stochasticEvolution = stochasticEvolution;        
    }
    
    @Override
    public Set<DefaultEdge> feedbackArcSet(DirectedGraph<Integer, DefaultEdge> g) {
        return feedbackArcSet(new SaabGraphWrapper(g));
    }

    private Set<DefaultEdge> feedbackArcSet(SaabGraph graph) {
        if(graph.vertexSet().size() == 1)
            return emptySet();
        
        Set<DefaultEdge> fas = new HashSet<>();
        List<SaabGraph> components = graph.components();
        if (components.size() == 1) {
            Bisection bisect = bisect(graph);
            List<SaabGraph> list = bisect.asSetList();
            fas.addAll(feedbackArcSet(list.get(0)));
            fas.addAll(feedbackArcSet(list.get(1)));
            fas.addAll(bisect.fas());
        } else 
            for (SaabGraph com : components)
                fas.addAll(feedbackArcSet(com));

        return fas;
    }

    private Bisection bisect(SaabGraph graph) {
        return stochasticEvolution.bisect(graph);
    }
    
    @Override
    public String toString() {
        return "Saab";
    }

}
