package PoLeungKuk;

import java.util.ArrayList;

public class Knowledge extends Formula {
	
	private Formula f;
	private int agent;
	
	/* If agent == 0, it concerns agent A
	 * If agent == 1, it concerns agent B
	 */
	
	public Knowledge (int agent, Formula f) {
		this.f = f;
		this.agent = agent;
	}
	
//	@Override
//	public boolean evaluate(PoLeungKuk.KripkeModel model, StateAB s) {
//		ArrayList<StateAB> connected = new ArrayList<StateAB>();
//		Iterator<PoLeungKuk.Relation> relationIter;
//
//		if(this.agent == 0) {
//			ArrayList<PoLeungKuk.Relation> relations = model.getRelationsX();
//			relationIter = relations.iterator();
//		} else {
//			ArrayList<PoLeungKuk.Relation> relations = model.getRelationsY();
//			relationIter = relations.iterator();
//		}
//
//		while(relationIter.hasNext()) {
//			PoLeungKuk.Relation r = relationIter.next();
//			if(r.isIncedent(s)) {
//				connected.add(r.connectedState(s));
//			}
//		}
//
//		Iterator<StateAB> connectedIter = connected.iterator();
//
//		while(connectedIter.hasNext()) {
//			StateAB t = connectedIter.next();
//			if(!f.evaluate(model, t)) {
//				return false;
//			}
//		}
//
//		return true;
//	}

	public boolean evaluate(KripkeModel model, State s) {
		ArrayList<State> connected = new ArrayList<State>();
		ArrayList<Relation> relations = new ArrayList<>();

		if(this.agent == 0) {
			relations = model.getRelationsX();
		} else if (this.agent == 1) {
			relations = model.getRelationsY();
		} else if (this.agent == 2) {
			relations = model.getRelationsZ();
		}

		connected.add(s);

		relations.forEach(relation -> {
			if(relation.isIncedent(s)) {
				connected.add(relation.connectedState(s));
			}
		});

		for (State t : connected) {
			if (!f.evaluate(model, t)) {
				return false;
			}
		}

		return true;
	}
	
	@Override
	public String toString() {
		String AB = (this.agent == 0) ? "A" : "B";
		return "K_" + AB + "(" + f + ")";
	}

}
