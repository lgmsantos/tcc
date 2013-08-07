package fas.algorithms.scattersearch;

public interface LocalSearchStrategy<T> {

    public T improve(T solution);
    
}
