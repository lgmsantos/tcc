package fas.algorithms.scattersearch;

import java.util.ArrayList;
import java.util.List;


public class ScatterSearchProcedure<T> {

    private DiversificationStrategy<T> diversificationStrategy;
    private CombinationStrategy<T, Iterable<T>> combinationStrategy;
    private LocalSearchStrategy<T> localSearchStrategy;
    private RefSet<T> refSet;

    public ScatterSearchProcedure(RefSet<T> refSet, DiversificationStrategy<T> diversificationStrategy,
            CombinationStrategy<T, Iterable<T>> combinationStrategy, LocalSearchStrategy<T> localSearchStrategy) {
        this.refSet = refSet;
        this.diversificationStrategy = diversificationStrategy;
        this.combinationStrategy = combinationStrategy;
        this.localSearchStrategy = localSearchStrategy;
    }

    public void search(T seedSolution) {
        List<T> solutions = new ArrayList<T>();
        for (T solution : diversificationStrategy.generate(seedSolution))
            solutions.add(localSearchStrategy.improve(solution));
        refSet.update(solutions);

        while (true) {
            List<T> newSolutions = new ArrayList<T>();
            for (SubSet<T> subSet : refSet.subSets())
                for (T solution : combinationStrategy.combine(subSet))
                    newSolutions.add(localSearchStrategy.improve(solution));
            refSet.update(newSolutions);
        }
    }
}
