package graph.algorithms.task.execution;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;

public class FASResult implements Comparable<FASResult> {

    private String algorithm;
    private int vertexSetSize;
    private int edgeSetSize;
    private long elapsed;
    private int fasSize;

    public FASResult(String algorithm, int vertexSetSize, int edgeSetSize, long elapsed, int fasSize) {
        this.algorithm = algorithm;
        this.vertexSetSize = vertexSetSize;
        this.edgeSetSize = edgeSetSize;
        this.elapsed = elapsed;
        this.fasSize = fasSize;
    }
    
    public int getFasSize() {
        return fasSize;
    }
    
    public int getVertexSetSize() {
        return vertexSetSize;
    }
    
    public int getEdgeSetSize() {
        return edgeSetSize;
    }

    @Override
    public String toString() {
        return format("%s/%.2fs", fasSize, elapsed / 1000.0);
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public int compareTo(FASResult other) {
        List<Comparable> order1 = naturalOrder();
        List<Comparable> order2 = other.naturalOrder();
        for (int i = 0; i < order1.size(); i++) {
            int cmp = order1.get(i).compareTo(order2.get(i));
            if (cmp != 0)
                return cmp;
        }
        return 0;
    }

    @SuppressWarnings("rawtypes")
    private List<Comparable> naturalOrder() {
        List<Comparable> order = new ArrayList<>();
        order.add(vertexSetSize);
        order.add(edgeSetSize);
        order.add(fasSize);
        order.add(elapsed);
        order.add(algorithm);
        return order;
    }
}
