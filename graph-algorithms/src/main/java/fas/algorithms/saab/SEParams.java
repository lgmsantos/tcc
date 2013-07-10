package fas.algorithms.saab;

public class SEParams {

    public final int iterationLimit;
    public final int iterationBonus;
    public final int deltaP;

    public SEParams(int iterationLimit, int iterationBonus, int deltaP) {
        this.iterationLimit = iterationLimit;
        this.iterationBonus = iterationBonus;
        this.deltaP = deltaP;
    }

    @Override
    public String toString() {
        return String.format("limit=%s, bonus=%s, delta=%s", iterationLimit, iterationBonus, deltaP);
    }
}
