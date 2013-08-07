package fas.algorithms.saab;

import java.util.HashSet;

public class IntSet extends HashSet<Integer>{

    private static final long serialVersionUID = 1L;
    
    private boolean[] containsMap = new boolean[1001];
    
    @Override
    public boolean add(Integer e) {
        containsMap[e] = true;
        return super.add(e);
    }
    
    @Override
    public boolean remove(Object e) {
        containsMap[(Integer) e] = false;
        return super.remove(e);
    }
    
    @Override
    public boolean contains(Object e) {
        return containsMap[(Integer) e];
    }
}
