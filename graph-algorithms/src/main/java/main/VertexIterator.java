package main;

import java.util.Iterator;

abstract class VertexIterator implements Iterator<Integer> {

    private Integer next;
    private Integer current;
    private FASGraphWrapper graph;

    public VertexIterator(FASGraphWrapper graph) {
        this.graph = graph;
        findNext();
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    private void findNext() {
        next = null;
        for (Integer v : graph.vertexSet())
            if (testVertex(v) && v != current) {
                next = v;
                break;
            }
    }

    abstract boolean testVertex(Integer vertex);

    @Override
    public Integer next() {
        current = next;
        findNext();
        return current;
    }

    @Override
    public void remove() {
        graph.removeVertex(current);
        findNext();
    }

}
