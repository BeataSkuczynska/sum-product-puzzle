package PoLeungKuk;

class Relation {

    /**
     * Class modelling relation between two states.
     */
    private final State first;
    private final State second;

    public Relation(State first, State second) {
        this.first = first;
        this.second = second;
    }

    public State getFirst() {
        return first;
    }

    public State getSecond() {
        return second;
    }

    public boolean isIncedent(State state) {
        return (state.equals(first) || state.equals(second));
    }

    public State connectedState(State state) {
        return (state.equals(first) ? second : first);
    }

    @Override
    public String toString() {
        return "<" + first + "," + second + ">";
    }
}
