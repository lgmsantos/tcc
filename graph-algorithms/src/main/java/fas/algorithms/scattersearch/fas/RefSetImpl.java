package fas.algorithms.scattersearch.fas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fas.algorithms.scattersearch.EvaluationStrategy;
import fas.algorithms.scattersearch.RefSet;
import fas.algorithms.scattersearch.SubSet;



public class RefSetImpl implements RefSet<Sequence> {

    Set<Integer> hashHistory = new HashSet<Integer>();
    Map<Integer, List<Sequence>> history = new HashMap<>();
    private List<Sequence> referenceList;
    private int maxSize;
    private EvaluationStrategy<Sequence> evaluationStrategy;

    public RefSetImpl(int maxSize, EvaluationStrategy<Sequence> evaluationStrategy) {
        this.maxSize = maxSize;
        this.evaluationStrategy = evaluationStrategy;
        this.referenceList = new ArrayList<>(maxSize);
    }

    @Override
    public void update(Collection<Sequence> solutions) {
        for (Sequence s : solutions)
            if (!visited(s))
                insert(s);
        System.out.println(evaluationStrategy.eval(referenceList.get(0)));
    }

    private void insert(Sequence s) {
        int insertIndex = 0;
        for (int i = referenceList.size() - 1; i >= 0; i++)
            if(evaluationStrategy.compare(referenceList.get(i), s) < 0)
                insertIndex = i + 1;
        
        if(insertIndex < maxSize){
            referenceList.add(insertIndex, s);
            historize(s);
        }
        
        if(referenceList.size() > maxSize)
            referenceList.remove(maxSize);        
    }

    private void historize(Sequence s) {
        if(!history.containsKey(s.hashCode()))
            history.put(s.hashCode(), new ArrayList<Sequence>());
        history.get(s.hashCode()).add(s);
    }

    private boolean visited(Sequence s) {
        if (history.containsKey(s.hashCode()))
            for (Sequence t : history.get(s.hashCode()))
                if (s.equals(t))
                    return true;
        return false;
    }

    @Override
    public Iterable<SubSet<Sequence>> subSets() {
        List<SubSet<Sequence>> sequence = new ArrayList<>();
        for(int i = 0; i < referenceList.size() - 2; i++)
            for(int j = i; j < referenceList.size() - 1; j++)
                sequence.add(new SubSet<Sequence>() {
                });
        return sequence;
    }
}
