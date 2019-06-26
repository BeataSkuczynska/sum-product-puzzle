package PoLeungKuk;

import java.util.ArrayList;
import java.util.Iterator;

class KripkeModel {

    /**
     * Class modelling Kripke model. There are both current and initial states, because the announcement of 2nd step
     * of Po Leung Kuk is about the initial states, not the ones after 1st announcement.
     */
    private final ArrayList<State> initialStates;
    private ArrayList<State> currentStates = new ArrayList<State>();
    private final ArrayList<Relation> relationsX;
    private final ArrayList<Relation> relationsY;
    private final ArrayList<Relation> relationsZ;

    public KripkeModel(ArrayList<State> states, ArrayList<Relation> relX, ArrayList<Relation> relY, ArrayList<Relation> relZ) {
        this.initialStates = states;
        this.relationsX = relX;
        this.relationsY = relY;
        this.relationsZ = relZ;
        states.forEach(state -> this.currentStates.add(new State(state.getNumX(), state.getNumY(), state.getNumZ())));
    }

    public ArrayList<State> getStatesList() {
        return currentStates;
    }

    public ArrayList<Relation> getRelationsX() {
        return relationsX;
    }

    public ArrayList<Relation> getRelationsY() {
        return relationsY;
    }

    public ArrayList<Relation> getRelationsZ() {
        return relationsZ;
    }

    public void addState(State s) {
        currentStates.add(s);
    }

    public void removeFromCurrentStates(State s) {
        currentStates.removeIf(state -> state.equals(s));
    }

    /**
     * Essential part of 2nd step. Compares currentStates (states after 1st announcement) and states to delete after
     * 2nd announcement and leaves only those who are not for deleating by 2nd announcement. After this step
     * currentStates are the same set as initialStates, because this distinction is no longer needed.
     * @param toDelete list of states to delete after 2nd announcement
     */
    public void compareAndRemove(ArrayList<State> toDelete) {
        toDelete.forEach(this::removeFromCurrentStates);

        ArrayList<State> toDeleteFromInitial = new ArrayList<State>();

        for (State stateI : initialStates) {
            if (currentStates.stream().noneMatch(stateC -> stateC.equals(stateI))) {
                toDeleteFromInitial.add(stateI);
            }
        }

        toDeleteFromInitial.forEach(this::removeState);
        this.currentStates = this.initialStates;
    }

    public void removeState(State state) {
        Iterator<Relation> iterX = relationsX.iterator();
        Iterator<Relation> iterY = relationsY.iterator();
        Iterator<Relation> iterZ = relationsZ.iterator();

        while (iterX.hasNext()) {
            /* remove all incedent accessibility relations of X
             * before the state is removed
             */
            Relation relX = iterX.next();

            if (relX.isIncedent(state)) {
                iterX.remove();
            }
        }

        while (iterY.hasNext()) {
            /* remove all incedent accessibility relations of Y
             * before the state is removed
             */
            Relation relY = iterY.next();

            if (relY.isIncedent(state)) {
                iterY.remove();
            }
        }

        while (iterZ.hasNext()) {
            /* remove all incedent accessibility relations of Z
             * before the state is removed
             */
            Relation relZ = iterZ.next();

            if (relZ.isIncedent(state)) {
                iterZ.remove();
            }
        }

        /* Now all incedent accessibility relations have been removed.
         * Time to remove the actual state
         */
        initialStates.remove(state);
    }

    public ArrayList<State> publicAnnouncement(Formula announcement) {
        ArrayList<State> toDelete = new ArrayList<State>();

        initialStates.forEach(state -> {
            if (!announcement.evaluate(this, state)) {
                toDelete.add(state);
            }
        });

        return toDelete;
    }

    public String toString() {
        StringBuilder output = new StringBuilder("States: (");

        currentStates.forEach(state -> output.append(state).append(", "));
        output.delete(output.length() - 2, output.length());

        output.append("), Relations X: (");
        relationsX.forEach(relation -> output.append(relation).append(", "));
        output.delete(output.length() - 2, output.length());

        output.append("), Relations Y: (");
        relationsY.forEach(relation -> output.append(relation).append(", "));
        output.delete(output.length() - 2, output.length());

        output.append("), Relations Z: (");
        relationsZ.forEach(relation -> output.append(relation).append(", "));
        output.delete(output.length() - 2, output.length());

        output.append(")");
        return output.toString();
    }


}