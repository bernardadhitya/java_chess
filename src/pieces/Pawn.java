package pieces;

import java.util.Vector;

public class Pawn extends Piece{
	public Pawn(int x, int y, char type) {
		super(x, y, type);
		// TODO Auto-generated constructor stub
	}
	boolean isFirstMove = true;
	public boolean isAvailable(int dest_x, int dest_y, Vector<Piece> team, Vector<Piece> enemy) {
		//here, make a method that will check if the move performed is possible
		
		//Pawn can only move one step ahead
		//	if they come from black, increment the index
		//	if they come from white, decrement the index
		//Pawn can also eat. Check if any black comes from the side-front
		//	if they come from black, [y+1][y+1] or [y+1][x-1]
		//	if they come from white, [y-1][x+1] or [y-1][x+1]
		
		if (Character.isLowerCase(this.getType())) {
			//Piece come from white
			//check if enemy is on destination
			System.out.println("Pawn of the White ");
			if (dest_y == this.getY()-1 && (Math.abs(dest_x - this.getX()) == 1)) {
				int flagEat = -1;
				for (int i=0; i<enemy.size(); i++) {
					if (enemy.get(i).getY()==dest_y && enemy.get(i).getX()==dest_x) {
						flagEat = i;
						break;
					}
				}
				if (flagEat != -1) {
					System.out.println("Proceed to attack...");
					this.setX(dest_x);
					this.setY(dest_y);
					enemy.get(flagEat).setAlive(false);
					//enemy.remove(flagEat);
					return true;
				}
			}
			if(dest_y == this.getY()-2 && dest_x == this.getX() && isFirstMove) {
				System.out.println("Proceed to use special move...");
				this.setX(dest_x);
				this.setY(dest_y);
				isFirstMove = false;
				return true;
			}
			else if (dest_y == this.getY()-1 && dest_x == this.getX()) {
				System.out.println("Proceed to move...");
				this.setX(dest_x);
				this.setY(dest_y);
				return true;
			}
			else {
				System.out.println("Fail to move...");
				return false;
			}
			
		}
		else {
			//Piece come from black
			System.out.println("Pawn of the Black ");
			if (dest_y == this.getY()+1 && (Math.abs(dest_x - this.getX()) == 1)) {
				int flagEat = -1;
				for (int i=0; i<enemy.size(); i++) {
					if (enemy.get(i).getY()==dest_y && enemy.get(i).getX()==dest_x) {
						flagEat = i;
						break;
					}
				}
				if (flagEat != -1) {
					System.out.println("Proceed to attack...");
					this.setX(dest_x);
					this.setY(dest_y);
					enemy.get(flagEat).setAlive(false);
					//enemy.remove(flagEat);
					return true;
				}
			}
			if(dest_y == this.getY()+2 && dest_x == this.getX() && isFirstMove) {
				System.out.println("Proceed to use special move...");
				this.setX(dest_x);
				this.setY(dest_y);
				isFirstMove = false;
				return true;
			}
			else if (dest_y == this.getY()+1 && dest_x == this.getX()) {
				System.out.println("Proceed to move...");
				this.setX(dest_x);
				this.setY(dest_y);
				return true;
			}
			else {
				System.out.println("Fail to move...");
				return false;
			}
		}
	}

}
