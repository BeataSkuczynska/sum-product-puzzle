package PoLeungKuk;

public class Relation {
	private State first, second;
	
	public Relation(State first, State second) {
		this.first = first;
		this.second = second;
	}
	
	public State getFirst() {
		return first;
	}
	
	public State getSecond() {
		return second;
	}
	
	public boolean isIncedent(State s) {
		return (s.equals(first) || s.equals(second));
	}
	
	public State connectedState(State s) {
		return (s.equals(first) ? second : first);
	}
	
	@Override
	public String toString() {
		return "<" + first + "," + second + ">";
	}
}
