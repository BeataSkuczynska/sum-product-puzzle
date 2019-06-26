package PoLeungKuk;

import java.util.ArrayList;

public class Knowledge extends Formula {

    /**
     * Class modelling Knowledge formulas. Evaluation of this formula is processed not only over given state, but also
     * on all states connected with this state by relations of agent, whose knowledge is evaluated.
     */

    private final Formula formula;
    private final int agent;


    Knowledge(int agent, Formula formula) {
        this.formula = formula;
        this.agent = agent;
    }

    public boolean evaluate(KripkeModel model, State state) {
        ArrayList<State> connectedStates = new ArrayList<State>();
        ArrayList<Relation> relations = new ArrayList<>();

        if (agent == 0) {
            relations = model.getRelationsX();
        } else if (agent == 1) {
            relations = model.getRelationsY();
        } else if (agent == 2) {
            relations = model.getRelationsZ();
        }

        connectedStates.add(state);

        relations.forEach(relation -> {
            if (relation.isIncedent(state)) {
                connectedStates.add(relation.connectedState(state));
            }
        });

        return connectedStates.stream().allMatch(connected -> formula.evaluate(model, connected));
    }

    @Override
    public String toString() {
        String agent;
        if (this.agent == 0) {
            agent = "X";
        } else if (this.agent == 1) {
            agent = "Y";
        } else {
            agent = "Z";
        }
        return "K_" + agent + "(" + formula + ")";
    }

}
