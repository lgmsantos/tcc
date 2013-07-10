package fas.algorithms.saab;

import static java.lang.Math.abs;
import static java.util.Arrays.asList;
import static java.util.Arrays.copyOf;
import static java.util.Collections.max;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import org.jgrapht.graph.DefaultEdge;

public class GraphBisection {

    private SaabGraph graph;
    private Random random;
    private Set<Integer>[] sets;
    private int[] setOf;
    private int cost;

    private GraphBisection() {

    }

    @SuppressWarnings("unchecked")
    public GraphBisection(SaabGraph graph) {
        this.random = new Random();
        this.graph = graph;
        setOf = new int[max(graph.vertexSet()) + 1];
        sets = new Set[2];
        sets[0] = new HashSet<>();
        sets[1] = new HashSet<>();
        splitVertices();
        initializeCost();
    }

    private void splitVertices() {
        for (Integer v : this.graph.vertexSet()) {
            setOf[v] = 0;
            sets[0].add(v);
        }
        outerWhile: while (true) {
            for (Integer v : this.graph.vertexSet()) {
                if (setOf[v] == 0 && random.nextBoolean())
                    move(v);
                if (abs(sets[0].size() - sets[1].size()) <= 1)
                    break outerWhile;
            }
        }
    }

    public void initializeCost() {
        cost = 0;
        for (Integer s : sets[1])
            for (Integer t : sets[0])
                if (graph.containsEdge(s, t))
                    cost++;
    }

    public int cost() {
        return cost;
    }

    public Set<DefaultEdge> fas() {
        Set<DefaultEdge> edges = new HashSet<>();
        for (Integer s : sets[1])
            for (Integer t : sets[0])
                if (graph.containsEdge(s, t))
                    edges.add(graph.getEdge(s, t));
        return edges;
    }

    private void move(Integer v) {
        cost -= gain(v);
        sets[setOf[v]].remove(v);
        ++setOf[v];
        setOf[v] %= 2;
        sets[setOf[v]].add(v);
    }

    public void perturb(int p) {
        @SuppressWarnings("unchecked")
        Queue<Integer>[] qs = new Queue[] { new LinkedList<>(), new LinkedList<>() };
        for (Integer v : graph.vertexSet()) {
            if (gain(v) > -random.nextInt(-p + 1)) {
                move(v);
                qs[setOf[v]].add(v);
            }
        }
        
        if (sets[0].size() != sets[1].size()) {
            double alpha = 0.9 * graph.vertexSet().size();
            int biggerSet = sets[0].size() > sets[1].size() ? 0 : 1;
            while (sets[biggerSet].size() > alpha && !qs[biggerSet].isEmpty())
                move(qs[biggerSet].poll());
        }

    }

    private int gain(Integer v) {
        int gain = 0;

        for (Integer t : graph.targetsOf(v))
            if (setOf[t] == 0)
                gain += setOf[v] == 0 ? -1 : 1;

        for (Integer s : graph.sourcesOf(v))
            if (setOf[s] == 1)
                gain += setOf[v] == 0 ? 1 : -1;

        return gain;
    }

    @SuppressWarnings("unchecked")
    public GraphBisection copy() {
        GraphBisection copy = new GraphBisection();
        copy.graph = graph;
        copy.random = random;
        copy.setOf = copyOf(setOf, setOf.length);
        copy.sets = new Set[2];
        copy.sets[0] = new HashSet<>(sets[0]);
        copy.sets[1] = new HashSet<>(sets[1]);
        copy.cost = cost;
        return copy;
    }

    public List<SaabGraph> asSetList() {
        return asList(graph.subgraph(sets[0]), graph.subgraph(sets[1]));
    }

}
