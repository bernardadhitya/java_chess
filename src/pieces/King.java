package pieces;

import java.util.Vector;

public class King extends Piece{

	public King(int x, int y, char type) {
		super(x, y, type);
		// TODO Auto-generated constructor stub
	}
	public boolean isAvailable(int dest_x, int dest_y, Vector<Piece> team, Vector<Piece> enemy) {
		if (Character.isLowerCase(this.getType())) {
			//Piece come from white
			//check if enemy is on destination
			System.out.println("King of the White ");
			if ((Math.abs(dest_y-this.getY())==1 || Math.abs(dest_x-this.getX())==1)
				&& checkMove(dest_x,dest_y,team,enemy)) {
				int flagEat = -1;
				for (int i=0; i<enemy.size(); i++) {
					if(enemy.get(i).getX()==dest_x && enemy.get(i).getY()==dest_y) {
						flagEat = i;
						enemy.get(flagEat).setAlive(false);
						break;
					}
				}
				if (flagEat == -1)
					System.out.println("Proceed to move...");
				else 
					System.out.println("Proceed to attack...");
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
			System.out.println("King of the Black ");
			if (Math.abs(dest_y-this.getY())==1 || Math.abs(dest_x-this.getX())==1
				&& checkMove(dest_x,dest_y,team,enemy)) {
				int flagEat = -1;
				for (int i=0; i<enemy.size(); i++) {
					if(enemy.get(i).getX()==dest_x && enemy.get(i).getY()==dest_y) {
						flagEat = i;
						enemy.get(flagEat).setAlive(false);
						break;
					}
				}
				if (flagEat == -1)
					System.out.println("Proceed to move...");
				else 
					System.out.println("Proceed to attack...");
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
	boolean checkMove(int dest_x, int dest_y, Vector<Piece> team, Vector<Piece> enemy) {
		for(int i=0; i<team.size(); i++) {
			if(team.get(i)==this) continue;
			if (dest_x==team.get(i).getX() && dest_y==team.get(i).getY()) {
				System.out.println("Team is on the way");
				return false;
			}
		}
		return true;
	}
}
