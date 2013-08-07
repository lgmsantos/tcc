package fas.algorithms.scattersearch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PermutationIterable implements Iterable<List<Integer>> {

    private List<Integer> numbers;
    private static final boolean LEFT = false;

    private class PermutationIterator implements Iterator<List<Integer>> {

        private List<Integer> next;
        private boolean[] directions;

        public PermutationIterator() {
            next = new ArrayList<>(numbers);
            directions = new boolean[next.size()];
        }

        @Override
        public boolean hasNext() {
            return next != null; 
        }

        @Override
        public List<Integer> next() {
            List<Integer> result = new ArrayList<>(next);
            if(maxMoveableIndex() != -1)
                maxSwap();
            else
                next = null;
            return result;
        }

        private void maxSwap() {
            int moveable = maxMoveableIndex();
            int moveableValue = next.get(moveable);
            swap(moveable, facing(moveable));
            for (int i = 0; i < next.size(); i++)
                if (next.get(i) > moveableValue)
                    directions[i] = !directions[i];
        }

        private void swap(int i, int j) {
            int aux = next.get(i);
            next.set(i, next.get(j));
            next.set(j, aux);
            boolean baux = directions[i];
            directions[i] = directions[j];
            directions[j] = baux;
        }

        private int facing(int i) {
            if (LEFT == directions[i])
                return i - 1;
            return i + 1;
        }

        private int maxMoveableIndex() {
            int maxi = -1;
            int maxValue = -1;
            for (int i = 0; i < next.size(); i++)
                if (moveable(i) && next.get(i) > maxValue) {
                    maxi = i;
                    maxValue = next.get(i);
                }
            return maxi;
        }

        private boolean moveable(int i) {
            int j = facing(i);
            if (j < 0 || j >= next.size())
                return false;
            return next.get(j) < next.get(i);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public PermutationIterable(List<Integer> numbers) {
        this.numbers = new ArrayList<>(numbers);
    }

    @Override
    public Iterator<List<Integer>> iterator() {
        return new PermutationIterator();
    }

}
