package PoLeungKuk;

public class Propositional extends Formula {
	
	/* PoLeungKuk.Propositional formulas are of the form s # (sum is #)
	 * or q # (sum of squares is #)
	 *
	 * For Po Leung Kuk version it can be also equality of ints
	 */
	private int a, b, c; //a > b


	public Propositional(State s) {
		this.a = s.getNumX();
		this.b = s.getNumY();
		this.c = s.getNumZ();
	}
	

	public boolean evaluate(KripkeModel model, State s) {
		return(s.getNumX() == a && s.getNumY() == b && s.getNumZ()  == c);
	}

	
	public int getA() {
		return a;
	}
	
	public int getB() {
		return b;
	}

	public int getC() {
		return c;
	}


	@Override
	public String toString() {
		return "(" + getA() + "," + getB() + "," + getC()+ ")";
	}
}
