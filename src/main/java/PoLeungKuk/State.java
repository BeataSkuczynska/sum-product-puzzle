package PoLeungKuk;

public class State {

    /**
     * Class modelling the state in Kripke model.
     */
    private final int numX;
    private final int numY;
    private final int numZ;

    public State(int numX, int numY, int numZ) {
        this.numX = numX;
        this.numY = numY;
        this.numZ = numZ;
    }

    public int getNumX() {
        return numX;
    }

    public int getNumY() {
        return numY;
    }


    @Override
    public String toString() {
        return "[" + numX + "," + numY + "," + numZ + "]";
    }

    public int getNumZ() {
        return numZ;
    }

    public boolean equals(State state) {
        return state.getNumX() == numX && state.getNumY() == numY && state.getNumZ() == numZ;
    }
}
