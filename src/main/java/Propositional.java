
public class Propositional extends Formula {
	
	/* Propositional formulas are of the form s # (sum is #)
	 * or q # (sum of squares is #)
	 */
	private int a, b; //a > b
	
	public Propositional(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	public Propositional(State s) {
		this.a = s.getNumA();
		this.b = s.getNumB();
	}
	
	@Override
	public boolean evaluate(KripkeModel model, State s) {
		return(s.getNumA() == a && s.getNumB() == b);
	}
	
	public int getA() {
		return a;
	}
	
	public int getB() {
		return b;
	}

	@Override
	public String toString() {
		return "(" + getA() + "," + getB() + ")";
	}
}
