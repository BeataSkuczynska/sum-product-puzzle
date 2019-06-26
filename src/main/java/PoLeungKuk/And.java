package PoLeungKuk;

import java.util.ArrayList;

public class And extends Formula {
    /**
     * Class modelling logical conjunction.
     */

    private final ArrayList<Formula> formulas;

    public And(ArrayList<Formula> formulas) {
        this.formulas = formulas;
    }

    @Override
    public boolean evaluate(KripkeModel model, State state) {

        return formulas.stream().allMatch(formula -> formula.evaluate(model, state));

    }

    public ArrayList<Formula> getFormulas() {
        return formulas;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("(");

        for (Formula formula : formulas) {
            output.append(formula);
            output.append(" & ");
        }

        output.delete(output.length() - 3, output.length());
        output.append(")");
        return output.toString();
    }
}
