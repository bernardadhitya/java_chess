package pieces;

import java.util.Vector;

public abstract class Piece {
	//positions
	private int x;
	private int y;
	protected char type;
	
	protected boolean isAlive;
	
	public Piece(int x, int y, char type) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
		this.isAlive = true;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	//set and get x
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	//set and get y
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	//set and get availability
	public abstract boolean isAvailable(int dest_x, int dest_y, Vector<Piece> team, Vector<Piece> enemy);
	
	

}
