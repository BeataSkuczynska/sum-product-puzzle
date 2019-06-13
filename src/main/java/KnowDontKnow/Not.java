package KnowDontKnow;

import KnowDontKnow.Formula;
import KnowDontKnow.KripkeModel;
import KnowDontKnow.State;

public class Not extends Formula {
	
	private Formula f;
	
	public Not (Formula f) {
		this.f = f;
	}
	
	@Override
	public boolean evaluate(KripkeModel model, State s) {
		return (!f.evaluate(model, s));
	}
	
	@Override
	public String toString() {
		return "not (" + f + ")";
	}

}
