import java.util.ArrayList;
import java.util.Iterator;

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
	
	@Override
	public boolean evaluate(KripkeModel model, State s) {
		ArrayList<State> connected = new ArrayList<State>();
		Iterator<Relation> relationIter;
		
		if(this.agent == 0) {
			ArrayList<Relation> relations = model.getRelationsAList();
			relationIter = relations.iterator();
		} else {
			ArrayList<Relation> relations = model.getRelationsBList();
			relationIter = relations.iterator();
		}
		
		while(relationIter.hasNext()) {
			Relation r = relationIter.next();
			if(r.isIncedent(s)) {
				connected.add(r.connectedState(s));
			}
		}
		
		Iterator<State> connectedIter = connected.iterator();
		
		while(connectedIter.hasNext()) {
			State t = connectedIter.next();
			if(!f.evaluate(model, t)) {
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
