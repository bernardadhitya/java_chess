package pieces;

import java.util.Vector;

public class Bishop extends Piece{
	int toAttack = -1;
	public Bishop(int x, int y, char type) {
		super(x, y, type);
		// TODO Auto-generated constructor stub
	}
	public boolean isAvailable(int dest_x, int dest_y, Vector<Piece> team, Vector<Piece> enemy) {
		toAttack = -1;
		if (Character.isLowerCase(this.getType())) {
			//Piece come from white
			//check if enemy is on destination
			System.out.println("Bishop of the White ");
			if (Math.abs(dest_y - this.getY()) == Math.abs(dest_x - this.getX()) && checkMove(dest_x, dest_y, team, enemy)) {
				if(toAttack != -1) {
					System.out.println("Proceed to attack...");
					this.setX(dest_x);
					this.setY(dest_y);
					enemy.get(toAttack).setAlive(false);
				}
				else {
					System.out.println("Proceed to move...");
					this.setX(dest_x);
					this.setY(dest_y);
				}
				return true;
			}
			else {
				System.out.println("Fail to move...");
				return false;
			}
			
		}
		else {
			//Piece come from black
			System.out.println("Bishop of the Black ");
			if (Math.abs(dest_y - this.getY()) == Math.abs(dest_x - this.getX()) && checkMove(dest_x, dest_y, team, enemy)) {
				if(toAttack != -1) {
					System.out.println("Proceed to attack...");
					this.setX(dest_x);
					this.setY(dest_y);
					enemy.get(toAttack).setAlive(false);
				}
				else {
					System.out.println("Proceed to move...");
					this.setX(dest_x);
					this.setY(dest_y);
				}
				return true;
			}
			else {
				System.out.println("Fail to move...");
				return false;
			}
		}
	}
	boolean checkMove(int dest_x, int dest_y, Vector<Piece> team, Vector<Piece> enemy) {
		//check rook move
		if (dest_y<this.getY()) {
			//check for enemy or ally in the upper range
			if (dest_x<this.getX()){
				// dest_x---->this
				for (int i=this.getX(); i>=dest_x; i--) {
					for (int j=0; j<enemy.size(); j++) {
						if(enemy.get(j).getX()==i && enemy.get(j).getY()==this.getY()-(this.getX()-i)) {
							if (i==dest_x) {
								//kill enemy
								toAttack = j;
								return true;
							}
							else {
								System.out.println("Enemy is on the way");
								return false;
							}
						}
					}
					for (int j=0; j<team.size(); j++) {
						if (team.get(j)==this) continue;
						if(team.get(j).getX()==i && team.get(j).getY()==this.getY()-(this.getX()-i)) {
							System.out.println("Team is on the way");
							return false;
						}
					}
				}
				return true;
			}
			else if (dest_x>this.getX()) {
				// this------->dest_x
				for (int i=this.getX(); i<=dest_x; i++) {
					for (int j=0; j<enemy.size(); j++) {
						if(enemy.get(j).getX()==i && enemy.get(j).getY()==this.getY()-(i-this.getX())) {
							if (i==dest_x) {
								//kill enemy
								toAttack = j;
								return true;
							}
							else {
								System.out.println("Enemy is on the way");
								return false;
							}
						}
					}
					for (int j=0; j<team.size(); j++) {
						if (team.get(j)==this) continue;
						if(team.get(j).getX()==i && team.get(j).getY()==this.getY()-(i-this.getX())) {
							System.out.println("Team is on the way");
							return false;
						}
					}
				}
				return true;
			}
		}
		else if (dest_y>this.getY()) {
			//check for enemy or ally in the lower range
			if (dest_x<this.getX()){
				// dest_y---->this
				for (int i=this.getX(); i>=dest_x; i--) {
					for (int j=0; j<enemy.size(); j++) {
						if(enemy.get(j).getX()==i && enemy.get(j).getY()==this.getY()+(this.getX()-i)) {
							if (i==dest_x) {
								//kill enemy
								toAttack = j;
								return true;
							}
							else {
								System.out.println("Enemy is on the way");
								return false;
							}
						}
						
					}
					for (int j=0; j<team.size(); j++) {
						if (team.get(j)==this) continue;
						if(team.get(j).getX()==i && team.get(j).getY()==this.getY()+(this.getX()-i)) {
							System.out.println("Team is on the way");
							return false;
						}
					}
				}
				return true;
			}
			else if (dest_x>this.getX()) {
				// this------->dest_y
				for (int i=this.getX(); i<=dest_x; i++) {
					for (int j=0; j<enemy.size(); j++) {
						if(enemy.get(j).getX()==i && enemy.get(j).getY()==this.getY()+(i-this.getX())) {
							if (i==dest_x) {
								//kill enemy
								toAttack = j;
								return true;
							}
							else {
								System.out.println("Enemy is on the way");
								return false;
							}
						}
					}
					for (int j=0; j<team.size(); j++) {
						if (team.get(j)==this) continue;
						if(team.get(j).getX()==i && team.get(j).getY()==this.getY()+(i-this.getX())) {
							System.out.println("Team is on the way");
							return false;
						}
					}
				}
				return true;
			}
		}
		return false;
	}
}
