
public abstract class Formula {
	public abstract boolean evaluate(KripkeModel model, State s);
	
	public abstract String toString();
}
