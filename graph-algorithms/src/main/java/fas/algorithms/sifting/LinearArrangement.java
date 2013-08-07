package fas.algorithms.sifting;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

import main.FASUtils;

public class LinearArrangement {

    private SiftingGraphWrapper graph;
    private List<Integer> elements;

    public LinearArrangement(SiftingGraphWrapper graph) {
        this.graph = graph;
        elements = new ArrayList<>();
    }

    public int size() {
        return elements.size();
    }

    public int[] addScore(Integer vertex) {
        if (elements.isEmpty())
            return new int[] { 0 };

        int[] scores = new int[elements.size() + 1];
        for (Integer v : elements)
            if (graph.containsEdge(vertex, v))
                scores[0]++;

        for (int i = 1; i < scores.length; i++) {
            scores[i] = scores[i - 1];
            if (graph.containsEdge(vertex, elements.get(i - 1)))
                scores[i]++;
            if (graph.containsEdge(elements.get(i - 1), vertex))
                scores[i]--;
        }

        return scores;
    }

    public void add(int index, Integer vertex) {
        elements.add(index, vertex);
    }

    public List<Integer> asList() {
        return unmodifiableList(elements);
    }

    public int score() {
//        int[] position = new int[max(elements) + 1];
//        int i = 0;
//        int score = 0;
//        for (Integer v : elements)
//            position[v] += i++;
//        for (DefaultEdge e : graph.edgeSet())
//            if (position[graph.getEdgeSource(e)] > position[graph.getEdgeTarget(e)])
//                score++;
//        return score;

        return FASUtils.feedbackArcSetFromPartialArrangement(graph.baseGraph(), elements).size();
    }

}
