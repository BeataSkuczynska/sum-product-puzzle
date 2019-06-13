package PoLeungKuk;

public class State {
	protected int numX, numY, numZ, sum;
	
	public State(int numX, int numY, int numZ) {
		this.numX = numX;
		this.numY = numY;
		this.numZ = numZ;
		this.sum = numX + numY + numZ;
	}
	
	public int getNumX() {
		return numX;
	}
	
	public int getNumY() {
		return numY;
	}
	
	public int getSum() {
		return sum;
	}

	
	@Override
	public String toString() {
		return "[" + numX + "," + numY + "," + numZ + "]";
	}

	public int getNumZ() {
		return numZ;
	}
}
