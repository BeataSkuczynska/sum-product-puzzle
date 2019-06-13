package KnowDontKnow;

import PoLeungKuk.Formula;
import PoLeungKuk.KripkeModel;
import PoLeungKuk.State;

import java.util.ArrayList;
import java.util.Iterator;

public class Or extends Formula {
	
	private ArrayList<Formula> formulas;
	
	public Or (ArrayList<Formula> formulas) {
		this.formulas = formulas;
	}
	
	@Override
	public boolean evaluate(KripkeModel model, State s) {
		Iterator<Formula> iter = formulas.iterator();
		
		while(iter.hasNext()) {
			Formula f = iter.next();
			
			if (f.evaluate(model, s)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
    public String toString() {
		Iterator<Formula> iter = formulas.iterator();
		StringBuilder output = new StringBuilder("(");
		output.append(iter.next());
		
		while(iter.hasNext()) {
			output.append(" | ");
			output.append(iter.next());
		}
		
		output.append(")");
		return output.toString();
	}

}
