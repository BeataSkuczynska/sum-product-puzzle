package KnowDontKnow;

import java.util.ArrayList;
import java.util.Iterator;

public class KripkeModel {
	private ArrayList<State> states;
	private ArrayList<Relation> relationsA;
	private ArrayList<Relation> relationsB;
	
	public KripkeModel(ArrayList<State> states, ArrayList<Relation> relationsA, ArrayList<Relation> relationsB) {
		this.states = states;
		this.relationsA = relationsA;
		this.relationsB = relationsB;
	}
	
	public ArrayList<State> getStatesList() {
		return states;
	}
	
	public ArrayList<Relation> getRelationsAList() {
		return relationsA;
	}
	
	public ArrayList<Relation> getRelationsBList() {
		return relationsB;
	}
	
	public void addState(State s) {
		states.add(s);
	}
	
	public void removeState(State s) {
		Iterator<Relation> iterA = relationsA.iterator();
		Iterator<Relation> iterB = relationsB.iterator();
		
		while(iterA.hasNext()) {
			/* remove all incedent accessibility relations of A 
			 * before the state is removed
			 */
			Relation relA = iterA.next();
			
			if(relA.isIncedent(s)) {
				iterA.remove();
			}
		}
		
		while(iterB.hasNext()) {
			/* remove all incedent accessibility relations of B 
			 * before the state is removed
			 */
			Relation relB = iterB.next();
			
			if(relB.isIncedent(s)) {
				iterB.remove();
			}
		}
		
		/* Now all incedent accessibility relations have been removed.
		 * Time to remove the actual state
		 */
		
		states.remove(s);
	}
	
	public void pAnnouncement(Formula ann) {
		ArrayList<State> toDelete = new ArrayList<State>();
		Iterator<State> stateIter = states.iterator();
		
		while(stateIter.hasNext()) {
			State s = stateIter.next();
			
			if(!ann.evaluate(this, s)) {
				toDelete.add(s);
			}
		}
		
		Iterator<State> deleteIter = toDelete.iterator();
		
		while(deleteIter.hasNext()) {
			State s = deleteIter.next();
			removeState(s);
		}
		
//		ArrayList<State> statesCopy = new ArrayList<State>(states);
//		Iterator<State> iter = statesCopy.iterator();
//		
//		while(iter.hasNext()) {
//			State s = iter.next();
//			
//			//System.out.println(s + "" + ann.evaluate(this, s));
//			if(!ann.evaluate(this, s)) {
//				removeState(s);
//			}
//		}
	}
	
	public String toString() {
		Iterator<State> iterS = states.iterator();
		Iterator<Relation> iterA = relationsA.iterator();
		Iterator<Relation> iterB = relationsB.iterator();
		
		StringBuilder output = new StringBuilder("States: (");
		output.append(iterS.next());
		
		while(iterS.hasNext()) {
			output.append(", ");
			output.append(iterS.next());
		}
		
		output.append("), Relations A: (");
		if(iterA.hasNext()) {
			output.append(iterA.next());
		}
		
		while(iterA.hasNext()) {
			output.append(", ");
			output.append(iterA.next());
		}
		
		output.append("), Relations B: (");
		if(iterB.hasNext()) {
			output.append(iterB.next());
		}
		
		while(iterB.hasNext()) {
			output.append(", ");
			output.append(iterB.next());
		}
		
		output.append(")");
		return output.toString();
	}
}