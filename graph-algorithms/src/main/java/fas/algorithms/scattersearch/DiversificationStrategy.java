package fas.algorithms.scattersearch;


public interface DiversificationStrategy<T> {

    Iterable<T> generate(T seed);
    
}
