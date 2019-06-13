package KnowDontKnow;

public class State {
	private int numA, numB, sum, sumsquares;
	
	public State(int numA, int numB) {
		this.numA = numA;
		this.numB = numB;
		this.sum = numA + numB;
		this.sumsquares = numA * numA + numB * numB;
	}
	
	public int getNumA() {
		return numA;
	}
	
	public int getNumB() {
		return numB;
	}
	
	public int getSum() {
		return sum;
	}
	
	public int getSumSquares() {
		return sumsquares;
	}
	
	@Override
	public String toString() {
		return "[" + numA + "," + numB + "]";
	}

}
