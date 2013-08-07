package fas.algorithms.saab;

import static java.lang.Math.random;
import static java.util.Arrays.asList;
import static java.util.Arrays.copyOf;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.jgrapht.graph.DefaultEdge;

public class Bisection {

    private SaabGraph graph;
    private Set<Integer> set1;
    private Set<Integer> set2;
    private int cost;
    private int[] gains;

    private Bisection() {
        set1 = new IntSet();
        set2 = new IntSet();
    }

    public Bisection(SaabGraph graph) {
        this.graph = graph;
        set1 = new IntSet();
        set2 = new IntSet();
        gains = new int[1001];
        initializeSets();
        initializeGains();
        cost = fas().size();
    }

    private void initializeGains() {
        for (int i = 0; i < gains.length; i++)
            gains[i] = 0;
        for (DefaultEdge e : graph.edgeSet()) {
            Integer s = graph.getEdgeSource(e);
            Integer t = graph.getEdgeTarget(e);
            if (set1.contains(s) && set1.contains(t))
                gains[s]--;
            else if (set2.contains(s) && set1.contains(t)) {
                gains[s]++;
                gains[t]++;
            } else if (set2.contains(s) && set2.contains(t))
                gains[t]--;
        }
    }

    private void initializeSets() {
        set2.addAll(graph.vertexSet());
        forever: while (true)
            for (Integer v : graph.vertexSet())
                if (random() < .5) {
                    move(v);
                    if (set1.size() >= set2.size())
                        break forever;
                }
    }

    public void perturb(int p) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        for (Integer v : graph.vertexSet())
            if (gains[v] > randint(p)) {
                move(v);
                (set1.contains(v) ? q1 : q2).add(v);
            }

        Queue<Integer> q = set1.size() > set2.size() ? q1 : q2;
        Set<Integer> v = set1.size() > set2.size() ? set1 : set2;
        while (v.size() > 0.6 * graph.vertexSet().size() && !q.isEmpty())
            move(q.poll());
    }

    private void move(Integer v) {
        cost -= gains[v];
        if (set1.contains(v))
            swap(v, set1, set2);
        else
            swap(v, set2, set1);
        updateGain(v);
    }

    private void updateGain(Integer v) {
        gains[v] = 0;
        for (Integer t : graph.targetsOf(v))
            if (set1.contains(t)) {
                if (set1.contains(v)) {
                    gains[v]--;
                    gains[t]--;
                } else {
                    gains[v]++;
                    gains[t]++;
                }
            } else
                gains[t] += set1.contains(v) ? 1 : -1;

        for (Integer s : graph.sourcesOf(v))
            if (set1.contains(s))
                gains[s] += set1.contains(v) ? -1 : 1;
            else {
                if (set1.contains(v)) {
                    gains[v]++;
                    gains[s]++;
                } else {
                    gains[v]--;
                    gains[s]--;
                }
            }
    }

    private void swap(Integer v, Set<Integer> orig, Set<Integer> dest) {
        orig.remove(v);
        dest.add(v);
    }

    private int randint(int p) {
        return (int) (random() * (p - 1));
    }

    public int cost() {
        return cost;
    }

    public Bisection copy() {
        Bisection copy = new Bisection();
        copy.graph = graph;
        copy.set1.addAll(set1);
        copy.set2.addAll(set2);
        copy.cost = cost;
        copy.gains = copyOf(gains, gains.length);
        return copy;
    }

    public List<SaabGraph> asSetList() {
        return asList(graph.subgraph(set1), graph.subgraph(set2));
    }

    public Set<DefaultEdge> fas() {
        Set<DefaultEdge> fas = new HashSet<>();
        for (DefaultEdge e : graph.edgeSet()) {
            Integer s = graph.getEdgeSource(e);
            Integer t = graph.getEdgeTarget(e);
            if (set2.contains(s) && set1.contains(t))
                fas.add(e);
        }
        return fas;
    }

}
