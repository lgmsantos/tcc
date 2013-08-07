package fas.algorithms.scattersearch;

import java.util.Comparator;


public interface EvaluationStrategy<T> extends Comparator<T>{
    
    int eval(T t);

}
