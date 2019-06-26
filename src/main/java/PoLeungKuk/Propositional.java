package PoLeungKuk;

public class Propositional extends Formula {

    private final int x;
    private final int y;
    private final int z;


    public Propositional(State state) {
        this.x = state.getNumX();
        this.y = state.getNumY();
        this.z = state.getNumZ();
    }

    public boolean evaluate(KripkeModel model, State s) {
        return (s.getNumX() == x && s.getNumY() == y && s.getNumZ() == z);
    }

    private int getX() {
        return x;
    }

    private int getY() {
        return y;
    }

    private int getZ() {
        return z;
    }


    @Override
    public String toString() {
        return "(" + getX() + "," + getY() + "," + getZ() + ")";
    }
}
