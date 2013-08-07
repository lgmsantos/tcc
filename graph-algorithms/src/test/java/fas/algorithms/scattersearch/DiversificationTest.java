package fas.algorithms.scattersearch;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import fas.algorithms.scattersearch.fas.Sequence;
import fas.algorithms.scattersearch.fas.SequenceFactory;

public class DiversificationTest {

    SequenceFactory sequenceFactory = new SequenceFactory();

    @Test
    public void sequenceDiversification() {
        Sequence sequence = sequenceFactory.newSequence(18);
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18]", sequence.toString());
//        assertEquals("[4, 8, 12, 16, 3, 7, 11, 15, 2, 6, 10, 14, 18, 1, 5, 9, 13, 17]", sequence.var(4).toString());
//        assertEquals("[5, 10, 15, 4, 9, 14, 3, 8, 13, 18, 2, 7, 12, 17, 1, 6, 11, 16]", sequence.var(5).toString());
    }

    @Test
    public void sanityHashCode() {
        List<Integer> arr = asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Set<Integer> hashes = new HashSet<>();
        int count = 0;
        for (List<Integer> arr2 : new PermutationIterable(arr)) {
            count++;
            int hash = sequenceFactory.newSequence(arr2).hashCode();
            Assert.assertTrue(count + "", hashes.add(hash));
        }
    }

}
