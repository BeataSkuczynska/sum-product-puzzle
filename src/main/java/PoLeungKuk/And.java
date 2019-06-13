package PoLeungKuk;

import java.util.ArrayList;
import java.util.Iterator;

public class And extends Formula {
	
	private ArrayList<Formula> formulas;
	
	public And (ArrayList<Formula> formulas) {
		this.formulas = formulas;
	}
	
	@Override
	public boolean evaluate(KripkeModel model, State s) {

		for (Formula f : formulas) {
			if (!f.evaluate(model, s)) {
				return false;
			}
		}
		
		return true;
	}

	public ArrayList<Formula> getFormulas() {
		return formulas;
	}
	
	@Override
    public String toString() {
		Iterator<Formula> iter = formulas.iterator();
		StringBuilder output = new StringBuilder("(");
		output.append(iter.next());
		
		while(iter.hasNext()) {
			output.append(" & ");
			output.append(iter.next());
		}
		
		output.append(")");
		return output.toString();
	}
}
