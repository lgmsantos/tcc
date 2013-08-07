package fas.algorithms.scattersearch;

import java.util.Collection;

public interface RefSet<T> {
    
    public void update(Collection<T> solution);

    public Iterable<SubSet<T>> subSets();

}
