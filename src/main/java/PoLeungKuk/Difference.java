package PoLeungKuk;

public class Difference extends Formula {

    private final int agent;
    Propositional formula;

    public Difference(int agent, Propositional prop) {
        this.formula = prop;
        this.agent = agent;
    }

    @Override
    public boolean evaluate(KripkeModel model, State s) {
        if (agent == 0) {
            return formula.getB() != formula.getC();
        } else if (agent == 1){
            return formula.getA() != formula.getB() && formula.getB() != formula.getC();
        }
        return false;
    }


    @Override
    public String toString() {
        return null;
    }
}
