package pieces;

import java.util.Vector;

public class Knight extends Piece{
	public Knight(int x, int y, char type) {
		super(x, y, type);
		// TODO Auto-generated constructor stub
	}
	public boolean isAvailable(int dest_x, int dest_y, Vector<Piece> team, Vector<Piece> enemy) {
		if (Character.isLowerCase(this.getType())) {
			//Piece come from white
			//check if enemy is on destination
			System.out.println("Knight of the White ");
			if ((dest_y == this.getY()+2 || dest_y == this.getY()-2) &&
				(dest_x == this.getX()+1 || dest_x == this.getX()-1)) {
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
			else if ((dest_y == this.getY()+1 || dest_y == this.getY()-1) &&
					(dest_x == this.getX()+2 || dest_x == this.getX()-2)) {
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
			System.out.println("Knight of the Black ");
			if ((dest_y == this.getY()+2 || dest_y == this.getY()-2) &&
					(dest_x == this.getX()+1 || dest_x == this.getX()-1)) {
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
				else if ((dest_y == this.getY()+1 || dest_y == this.getY()-1) &&
						(dest_x == this.getX()+2 || dest_x == this.getX()-2)) {
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
}
