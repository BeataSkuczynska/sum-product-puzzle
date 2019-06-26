package PoLeungKuk;

abstract class Formula {

    /**
     * Abstract class modelling logical formula.
     * Evaluation of the formula is proceeded given Kripke model and one of its states.
     */

    public abstract boolean evaluate(KripkeModel model, State state);

    public abstract String toString();
}
