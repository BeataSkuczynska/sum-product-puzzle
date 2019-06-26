package PoLeungKuk;

public class Difference extends Formula {

    /**
     * Class modelling predicate Difference. Depending on whether it's agent 0 (Xavier) or agent 1 (Yvo) checks checks
     * difference of either y and z values in evaluated state or x, y, z.
     */

    private final int agent;

    public Difference(int agent) {
        this.agent = agent;
    }

    @Override
    public boolean evaluate(KripkeModel model, State s) {
        if (agent == 0) {
            return s.getNumY() != s.getNumZ();
        } else if (agent == 1) {
            return s.getNumX() != s.getNumY() && s.getNumY() != s.getNumZ() && s.getNumX() != s.getNumZ();
        }
        return false;
    }


    @Override
    public String toString() {
        return "D";
    }
}
