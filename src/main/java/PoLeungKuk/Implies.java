package PoLeungKuk;

public class Implies extends Formula {

    /**
     * Class modelling logical implication.
     */

    private final Formula antecedent, conclusion;

    Implies(Formula antecedent, Formula conclusion) {
        this.antecedent = antecedent;
        this.conclusion = conclusion;
    }

    @Override
    public boolean evaluate(KripkeModel model, State state) {
        if (!antecedent.evaluate(model, state)) {
            return true;
        } else {
            return (conclusion.evaluate(model, state));
        }
    }

    @Override
    public String toString() {
        return antecedent + " -> " + conclusion;
    }

}
