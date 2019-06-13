package PoLeungKuk;

public class Implies extends Formula {
	
	private Formula f1, f2;
	
	public Implies (Formula f1,  Formula f2) {
		this.f1 = f1;
		this.f2 = f2;
	}
	
	@Override
	public boolean evaluate(KripkeModel model, State s) {
		if(!f1.evaluate(model, s)) {
			return true;
		} else {
			return(f2.evaluate(model, s));
		}
	}
	
	@Override
	public String toString() {
		return f1 + " -> " + f2;
	}

}
