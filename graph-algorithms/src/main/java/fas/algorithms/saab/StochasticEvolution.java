package fas.algorithms.saab;


public class StochasticEvolution {

    private SEParams params;

    public StochasticEvolution(SEParams params) {
        this.params = params;
    }

    public Bisection bisect(SaabGraph graph) {
        Bisection bisection = new Bisection(graph);
        Bisection best = bisection.copy();
        int count = 0;
        int p0 = -1;
        int p = p0;
        while (count < params.iterationLimit) {
            int preCost = bisection.cost();
            bisection.perturb(p);
            if (bisection.cost() < best.cost()) {
                best = bisection.copy();
                count -= params.iterationLimit;
            } else
                count++;

            if (preCost == bisection.cost())
                p -= params.deltaP;
            else
                p = p0;
        }

        return best;
    }
}
