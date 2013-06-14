package fas.algorithms.saab;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class StochasticEvolutionBisect {

    private DirectedGraph<Integer, DefaultEdge> graph;
    private Random random;

    public StochasticEvolutionBisect(Random random, DirectedGraph<Integer, DefaultEdge> graph) {
        this.random = random;
        this.graph = graph;
    }

    public List<Set<Integer>> bisect() {
        Bisection bisection = new Bisection(random, graph);
        Bisection best = bisection.copy();
        
        int preCost = 0;
        int posCost = bisection.cost();
        int bestCost = posCost; 
        
        int counter = 0;        
        int iterationLimit = 1000;
        int iterationBonus = 10;
        
        int delta = 1;
        int p0 = 0;
        int p = p0;
        
        while(counter < iterationLimit){
            preCost = posCost;
            bisection.perturb(p);
            posCost = bisection.cost();
            if(posCost < bestCost){
                bestCost = posCost;
                best = bisection.copy();
                counter -= iterationBonus;
            }else{
                counter++;
            }
            
            if(preCost == posCost)
                p -= delta;
            else
                p = p0;
        }
        
        return best.asSetList();
    }
}

