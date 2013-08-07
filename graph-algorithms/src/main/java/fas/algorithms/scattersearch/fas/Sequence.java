package fas.algorithms.scattersearch.fas;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sequence {

    private List<Integer> numbers;
    private Integer hashCode;

    public Sequence(List<Integer> numbers) {
        this.numbers = unmodifiableList(new ArrayList<>(numbers));
    }

    @Override
    public int hashCode() {
        if (hashCode == null)
            hashCode = numbers.hashCode();
        return hashCode;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    public int size() {
        return numbers.size();
    }

    public Sequence reverse() {
        List<Integer> rev = new ArrayList<>(numbers);
        Collections.reverse(rev);
        return new Sequence(rev);
    }

    public Integer get(int i) {
        return numbers.get(i);
    }

    public List<Integer> asList() {
        return numbers;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!Sequence.class.equals(obj.getClass()))
            return false;
        return ((Sequence)obj).numbers.equals(numbers);
    }
}
