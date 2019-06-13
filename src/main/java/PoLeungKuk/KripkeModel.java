package PoLeungKuk;

import java.util.ArrayList;
import java.util.Iterator;

public class KripkeModel {
	private ArrayList<State> states;
	private ArrayList<Relation> relationsX;
	private ArrayList<Relation> relationsY;
	private ArrayList<Relation> relationsZ;

	public KripkeModel(ArrayList<State> states, ArrayList<Relation> relationsX, ArrayList<Relation> relationsY, ArrayList<Relation> relationsZ) {
		this.states = states;
		this.relationsX = relationsX;
		this.relationsY = relationsY;
		this.relationsZ = relationsZ;
	}

	public ArrayList<State> getStatesList() {
		return states;
	}

	public ArrayList<Relation> getRelationsX() {
		return relationsX;
	}
	
	public ArrayList<Relation> getRelationsY() {
		return relationsY;
	}

	public ArrayList<Relation> getRelationsZ() {
		return relationsZ;
	}

	public void addState(State s) {
		states.add(s);
	}
	
	public void removeState(State s) {
		Iterator<Relation> iterX = relationsX.iterator();
		Iterator<Relation> iterY = relationsY.iterator();
		Iterator<Relation> iterZ = relationsZ.iterator();

		while(iterX.hasNext()) {
			/* remove all incedent accessibility relations of X
			 * before the state is removed
			 */
			Relation relX = iterX.next();
			
			if(relX.isIncedent(s)) {
				iterX.remove();
			}
		}
		
		while(iterY.hasNext()) {
			/* remove all incedent accessibility relations of Y
			 * before the state is removed
			 */
			Relation relY = iterY.next();
			
			if(relY.isIncedent(s)) {
				iterY.remove();
			}
		}

		while(iterZ.hasNext()) {
			/* remove all incedent accessibility relations of Z
			 * before the state is removed
			 */
			Relation relZ = iterZ.next();

			if(relZ.isIncedent(s)) {
				iterZ.remove();
			}
		}
		
		/* Now all incedent accessibility relations have been removed.
		 * Time to remove the actual state
		 */
		
		states.remove(s);
	}
	
//	public void pAnnouncementAB(PoLeungKuk.Formula ann) {
//		ArrayList<StateAB> toDelete = new ArrayList<StateAB>();
//		Iterator<StateAB> stateIter = statesAB.iterator();
//
//
//		while(stateIter.hasNext()) {
//			StateAB s = stateIter.next();
//
//			if(!ann.evaluate(this, s)) {
//				toDelete.add(s);
//			}
//		}
//
//		Iterator<StateAB> deleteIter = toDelete.iterator();
//
//		while(deleteIter.hasNext()) {
//			StateAB s = deleteIter.next();
//			removeState(s);
//		}
		
//		ArrayList<PoLeungKuk.State> statesCopy = new ArrayList<PoLeungKuk.State>(states);
//		Iterator<PoLeungKuk.State> iter = statesCopy.iterator();
//		
//		while(iter.hasNext()) {
//			PoLeungKuk.State s = iter.next();
//			
//			//System.out.println(s + "" + ann.evaluate(this, s));
//			if(!ann.evaluate(this, s)) {
//				removeState(s);
//			}
//		}
//	}

	public void pAnnouncement(Formula ann) {
		ArrayList<State> toDelete = new ArrayList<State>();

		states.forEach(state -> {
			if(!ann.evaluate(this, state)) {
				toDelete.add(state);
			}
		});

		toDelete.forEach(this::removeState);
	}
	
	public String toString() {
		/*
			TODO: Currently prints only KnowDontKnow.KnowDontKnow elements
		 */
		Iterator<State> iterS = states.iterator();
		Iterator<Relation> iterA = relationsX.iterator();
		Iterator<Relation> iterB = relationsY.iterator();
		
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