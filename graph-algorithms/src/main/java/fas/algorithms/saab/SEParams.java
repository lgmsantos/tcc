package fas.algorithms.saab;

public class SEParams {

    public final int iterationLimit;
    public final int deltaP;

    public SEParams(int iterationLimit, int deltaP) {
        this.iterationLimit = iterationLimit;
        this.deltaP = deltaP;
    }

    @Override
    public String toString() {
        return String.format("limit=%s, delta=%s", iterationLimit, deltaP);
    }
}
