package fas.algorithms.scattersearch;

public interface CombinationStrategy<T, R> {
    
    public R combine(SubSet<T> subSet);

}
