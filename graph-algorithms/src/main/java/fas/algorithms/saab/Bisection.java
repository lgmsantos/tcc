package fas.algorithms.saab;

import static java.lang.Integer.MAX_VALUE;
import static java.util.Arrays.asList;
import static java.util.Arrays.copyOf;
import static java.util.Collections.max;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

class Bisection {

    private DirectedGraph<Integer, DefaultEdge> graph;
    private Random random;
    private int[] set;
    private int[] setSize;

    public Bisection(Random random, DirectedGraph<Integer, DefaultEdge> graph) {
        this.random = random;
        this.graph = graph;
        set = new int[max(graph.vertexSet()) + 1];
        setSize = new int[2];
        for (Integer v : graph.vertexSet()) {
            set[v] = random.nextInt(2);
            setSize[set[v]]++;
        }
    }

    public int cost() {
        int cost = 0;
        if (setSize[0] == 0 || setSize[1] == 0)
            return MAX_VALUE;
        for (DefaultEdge e : graph.edgeSet())
            if (set[graph.getEdgeSource(e)] != set[graph.getEdgeTarget(e)])
                cost++;
        return cost;
    }

    private void move(Integer v) {
        setSize[set[v]]--;
        ++set[v];
        set[v] %= 2;
        setSize[set[v]]++;
    }

    public void perturb(int p) {
        @SuppressWarnings("unchecked")
        Queue<Integer>[] qs = new Queue[] { new LinkedList<>(), new LinkedList<>() };

        for (Integer v : graph.vertexSet()) {
            if (gain(v) > -random.nextInt(-p + 1))
                move(v);
            qs[set[v]].add(v);
        }

        if (setSize[0] != setSize[1]) {
            double alpha = 0.6 * graph.vertexSet().size();
            int biggerSet = setSize[0] > setSize[1] ? 0 : 1;
            while (setSize[biggerSet] > alpha) {
                move(qs[biggerSet].poll());
            }
        }
    }

    private int gain(Integer v) {
        int gain = 0;
        
        if (set[v] == 0) {
            for (DefaultEdge e : graph.incomingEdgesOf(v))
                gain += (set[graph.getEdgeSource(e)] == 1) ? 1 : 0;
            for (DefaultEdge e : graph.outgoingEdgesOf(v))
                gain += (set[graph.getEdgeTarget(e)] == 0) ? -1 : 0;
        } else {
            for (DefaultEdge e : graph.incomingEdgesOf(v))
                gain += (set[graph.getEdgeSource(e)] == 1) ? -1 : 0;
            for (DefaultEdge e : graph.outgoingEdgesOf(v))
                gain += (set[graph.getEdgeTarget(e)] == 0) ? 1 : 0;
        }

        return gain;
    }

    public Bisection copy() {
        Bisection copy = new Bisection(random, graph);
        copy.set = copyOf(set, set.length);
        copy.setSize = copyOf(setSize, setSize.length);
        return copy;
    }

    public List<Set<Integer>> asSetList() {
        @SuppressWarnings("unchecked")
        Set<Integer>[] sets = new Set[] { new HashSet<>(), new HashSet<>() };
        for (Integer v : graph.vertexSet())
            sets[set[v]].add(v);
        return asList(sets);
    }
}
