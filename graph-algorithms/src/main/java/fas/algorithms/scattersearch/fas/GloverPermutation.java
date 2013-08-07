package fas.algorithms.scattersearch.fas;

import java.util.ArrayList;
import java.util.List;

import fas.algorithms.scattersearch.DiversificationStrategy;

public class GloverPermutation implements DiversificationStrategy<Sequence>{

    @Override
    public Iterable<Sequence> generate(Sequence seed) {
        List<Sequence> results = new ArrayList<>();
        for(int i = 2; i < seed.size() / 2; i++) {
            Sequence v = var(seed, i);
            results.add(v);
            results.add(v.reverse());
        }
        return results;
    }
    
    private Sequence var(Sequence numbers, int h) {
        List<Integer> list = new ArrayList<>();
        for(int i = h; i > 0; i--)
            for(int j = i; j <= numbers.size(); j += h) 
                list.add(numbers.get(j - 1));
        return new Sequence(list);
    }    

}
