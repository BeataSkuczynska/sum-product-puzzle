package PoLeungKuk;

import java.util.ArrayList;
import java.util.Iterator;

public class KripkeModel {
	private ArrayList<State> initialStates;
	private ArrayList<State> currentStates = new ArrayList<State>();
	private ArrayList<Relation> relationsX;
	private ArrayList<Relation> relationsY;
	private ArrayList<Relation> relationsZ;

	public KripkeModel(ArrayList<State> states, ArrayList<Relation> relationsX, ArrayList<Relation> relationsY, ArrayList<Relation> relationsZ) {
		this.initialStates = states;
		this.relationsX = relationsX;
		this.relationsY = relationsY;
		this.relationsZ = relationsZ;
		states.forEach(state -> this.currentStates.add(new State(state.getNumX(), state.getNumY(), state.getNumZ())));
	}

	public ArrayList<State> getStatesList() {
		return currentStates;
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
		initialStates.add(s);
	}

	public void removeFromCurrentStates(State s){
		currentStates.removeIf(state -> state.equals(s));
	}

	public void compareAndRemove(ArrayList<State> toDelete) {
		toDelete.forEach(this::removeFromCurrentStates);

		ArrayList<State> toDeleteFromInitial = new ArrayList<State>();

		for (State stateI : initialStates){
			if (currentStates.stream().noneMatch(stateC -> stateC.equals(stateI))){
				toDeleteFromInitial.add(stateI);
			}
		}

		toDeleteFromInitial.forEach(this::removeState);
		this.currentStates = this.initialStates;
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

		/* remove all incedent accessibility relations of X
		 * before the state is removed
		 */
		initialStates.remove(s);
	}

	public ArrayList<State> pAnnouncement(Formula ann) {
		ArrayList<State> toDelete = new ArrayList<State>();

		initialStates.forEach(state -> {
			if(!ann.evaluate(this, state)) {
				toDelete.add(state);
			}
		});

		return toDelete;
	}
	
	public String toString() {
		/*
			TODO: Currently prints only KnowDontKnow.KnowDontKnow elements
		 */
		Iterator<State> iterS = initialStates.iterator();
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