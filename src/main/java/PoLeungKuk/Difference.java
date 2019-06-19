package PoLeungKuk;

public class Difference extends Formula {

    private final int agent;
    private Propositional prop;

    public Difference(int agent, Propositional prop) {
        this.prop = prop;
        this.agent = agent;
    }

    @Override
    public boolean evaluate(KripkeModel model, State s) {
            if (agent == 0) {
                return s.getNumY() != s.getNumZ();
            } else if (agent == 1){
                return s.getNumX() != s.getNumY() && s.getNumY() != s.getNumZ() && s.getNumX() != s.getNumZ();
            }
        return false;
    }


    @Override
    public String toString() {
        return null;
    }
}
