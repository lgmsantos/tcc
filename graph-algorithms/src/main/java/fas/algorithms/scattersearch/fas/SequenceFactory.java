package fas.algorithms.scattersearch.fas;

import java.util.ArrayList;
import java.util.List;


public class SequenceFactory {

    public Sequence newSequence(int max) {
        List<Integer> numbers = new ArrayList<>();
        for(int i = 1; i <= max; i++)
            numbers.add(i);
            
        return new Sequence(numbers);
    }

    public Sequence newSequence(List<Integer> numbers) {
        return new Sequence(numbers);
    }
    
}
