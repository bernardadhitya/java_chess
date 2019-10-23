import java.io.*;
import java.util.*;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class Main {
	int counter = 0;
	int exception = 0;
	Vector<String> names = new Vector<String>();
	Vector<Integer> scores = new Vector<Integer>();
	Scanner scan = new Scanner(System.in);
	void viewMenu() {
		System.out.println("POWER CHESS MATE");
		System.out.println("1. Play");
		System.out.println("2. Result");
		System.out.println("3. Save and Exit");
	}
	void clrscr() {
		for (int i=0; i<50; i++) {
			System.out.println();
		}
	}
	
	//main functions
	void play() {
		
		//chess board setup
		Vector<Piece> team_white = new Vector<Piece>();
		Vector<Character> team_white_dead = new Vector<Character>();
		Vector<Piece> team_black = new Vector<Piece>();
		Vector<Character> team_black_dead = new Vector<Character>();
		clrscr();
		
		//input name
		String player1, player2;
		do {
			System.out.print("Insert Player 1 Name [3-20] >>");
			player1 = scan.nextLine();
		} while (player1.length()<3||player1.length()>20);
		System.out.println();
		do {
			System.out.print("Insert Player 2 Name [3-20] >>");
			player2 = scan.nextLine();
		} while (player2.length()<3||player2.length()>20);
		
		//initialize both team
		for (int i=0; i<8; i++)
			team_black.add(new Pawn(i,1,'P'));
		team_black.add(new Rook(0,0,'R'));
		team_black.add(new Rook(7,0,'R'));
		team_black.add(new Knight(1,0,'N'));
		team_black.add(new Knight(6,0,'N'));
		team_black.add(new Bishop(2,0,'B'));
		team_black.add(new Bishop(5,0,'B'));
		team_black.add(new Queen(3,0, 'Q'));
		team_black.add(new King(4,0, 'K'));
		
		for(int i=0; i<8; i++)
			team_white.add(new Pawn(i,6,'p'));
		team_white.add(new Rook(0,7,'r'));
		team_white.add(new Rook(7,7,'r'));
		team_white.add(new Knight(1,7,'n'));
		team_white.add(new Knight(6,7,'n'));
		team_white.add(new Bishop(2,7,'b'));
		team_white.add(new Bishop(5,7,'b'));
		team_white.add(new Queen(3,7,'q'));
		team_white.add(new King(4,7,'k'));
		String input = "";
		int turn = 0;
		char [] temp;
		//load board
		do {
			clrscr();
			input = "";
			System.out.println("           BLACK");
			System.out.println("   A  B  C  D  E  F  G  H ");
			for (int i=0; i<8; i++) {
				System.out.print((i+1) + " ");
				for (int j=0; j<8; j++) {
					System.out.print("[");
					printPiece(j,i, team_white, team_black);
					System.out.print("]");
				}
				System.out.print(" " + (i+1));
				System.out.println();
			}
			System.out.println("   A  B  C  D  E  F  G  H ");
			System.out.println("           WHITE");
			System.out.print("Player 1 - Piece: ");
			for (int i=0; i<team_white_dead.size(); i++)
				System.out.print(team_white_dead.get(i) + " ");
			System.out.println();
			System.out.print("Player 2 - Piece: ");
			for (int i=0; i<team_black_dead.size(); i++)
				System.out.print(team_black_dead.get(i) + " ");
			System.out.println();
			
			do{
				if (turn%2==0) {
					System.out.println("PLAYER 1 [WHITE] MOVE");
					System.out.println("Input piece you want to move [(xy) || surrender] >>");
				}
				else {
					System.out.println("PLAYER 2 [BLACK] MOVE");
					System.out.println("Input piece you want to move [(xy) || surrender] >>");
				}
				input = scan.nextLine();
				temp = input.toCharArray();
				if (input.equals("surrender")) break;
			} while (input.length()!=2 || !Character.isLetter(temp[0]) || !Character.isDigit(temp[1]));
			
			if (!input.equals("surrender")) {
				int x = (int)(Character.toLowerCase(temp[0])) - 96;
				int y = Character.getNumericValue(temp[1]);
				if (x<1||x>8||y<1||y>8) {
					System.out.println("Invalid move");
				}
				else {
					//System.out.println(x + " " + y);
					x -= 1;
					y -= 1;
					int flag = 0;
					if (turn%2==0) {
						//check if the selected piece is from white
						for (int i=0; i<team_white.size(); i++) {
							if (team_white.get(i).getX()==x && team_white.get(i).getY()==y) {
								movePiece(x,y,team_white, team_black, team_white.get(i));
								flag = 1;
							}
						}
						if (flag==0) {
							System.out.println("Cannot select this grid");
						}
					}
					else {
						//check if the selected piece is from black
						for (int i=0; i<team_black.size(); i++) {
							if (team_black.get(i).getX()==x && team_black.get(i).getY()==y) {
								movePiece(x,y,team_black, team_white, team_black.get(i));
								flag = 1;
							}
						}
						if (flag==0) {
							System.out.println("Cannot select this grid");
						}
					}
					if (exception == 1) exception = 0;
					else if (flag==1) turn += 1;
				}
				for (int i=0; i<team_white.size(); i++) {
					if (!team_white.get(i).isAlive()) {
						team_white_dead.add(team_white.get(i).getType());
						team_white.remove(i);
					}
				}
				for (int i=0; i<team_black.size(); i++) {
					if (!team_black.get(i).isAlive()) {
						team_black_dead.add(team_black.get(i).getType());
						team_black.remove(i);
					}
				}
			}
		} while (!input.equals("surrender")&&!team_black_dead.contains('K')&&!team_white_dead.contains('k'));
		System.out.println();
		if (team_black_dead.contains('K')) {
			//player 1 wins
			clrscr();
			System.out.println("        Congratulations, " + player1);
			System.out.println("                 Score: " + (team_black_dead.size()*25));
			names.add(player1);
			scores.add(team_black_dead.size()*25);
			counter += 1;
		}
		else if (team_white_dead.contains('k')) {
			//player 2 wins
			clrscr();
			System.out.println("        Congratulations, " + player2);
			System.out.println("                 Score: " + (team_white_dead.size()*25));
			names.add(player2);
			scores.add(team_white_dead.size()*25);
			counter += 1;
		}
		System.out.println("Press enter to continue...");
		scan.nextLine();
		//delete all the remaining pieces
		for (int i=0; i<team_white.size(); i++) team_white.remove(0);
		for (int i=0; i<team_black.size(); i++) team_black.remove(0);
		for (int i=0; i<team_white_dead.size(); i++) team_white_dead.remove(0);
		for (int i=0; i<team_black_dead.size(); i++) team_black_dead.remove(0);
		
	}
	
	void result() {
		clrscr();
		int temp = 0;
		String tempName = "";
		for (int i=0; i<counter; i++) {
			for (int j=0; j<counter; j++) {
				if(scores.get(i)>scores.get(j)) {
					temp = scores.get(i);
					tempName = names.get(i);
					scores.set(i, scores.get(j));
					names.set(i, names.get(j));
					scores.set(j, temp);
					names.set(j, tempName);
				}
			}
		}
		System.out.println("          Winner Score");
		System.out.println("==================================");
		System.out.println("      Name      ||      Score      ");
		System.out.println("==================================");
		for (int i=0; i<scores.size(); i++) {
			System.out.printf("    %-20s   %5d\n", names.get(i), scores.get(i));
		}
		System.out.println("==================================");
		scan.nextLine();
	}
	void saveAndExit() {
		
	}
	void printPiece(int x, int y, Vector<Piece> team1, Vector<Piece> team2) {
		
		for (int i=0; i<team1.size(); i++) {
			if (team1.get(i).getX()==x && team1.get(i).getY()==y) {
				System.out.print(team1.get(i).getType());
				return;
			}
		}
		for (int i=0; i<team2.size();i++) {
			if (team2.get(i).getX()==x && team2.get(i).getY()==y) {
				System.out.print(team2.get(i).getType());
				return;
			}
		}
		System.out.print(" ");
	}
	void movePiece(int x, int y, Vector<Piece> team, Vector<Piece> enemy, Piece toMove) {
		String input = "";
		char [] temp;
		int dest_x = 0;
		int dest_y = 0;
		do {
			do {
				System.out.println("Input destination you want to be placed [(xy) || cancel] >>");
				input = scan.nextLine();
				temp = input.toCharArray();
				if (input.equals("cancel")) {
					exception = 1;
					return;
				}
				dest_x = (int)(Character.toLowerCase(temp[0])) - 96;
				dest_y = Character.getNumericValue(temp[1]);
				//System.out.println(dest_x + " " + dest_y);
				
				if (input.length()!=2||dest_x<1||dest_x>8||dest_y<1||dest_y>8) {
					System.out.println("Invalid input");
				}
			} while (input.length()!=2||dest_x<1||dest_x>8||dest_y<1||dest_y>8);
			
			dest_x -= 1;
			dest_y -= 1;
			
		} while(toMove.isAvailable(dest_x, dest_y, team, enemy)==false);
	}
	public Main() {
		// TODO Auto-generated constructor stub
		int input = 0;
		readData();
		do {
			clrscr();
			viewMenu();
			try {
				input = scan.nextInt();
			} catch (Exception e) {
				System.out.println("Please input correctly!");
			} finally {
				scan.nextLine();
			}
			switch(input) {
				case 1:
					play();
					break;
				case 2:
					result();
					break;
				case 3:
					writeData();
					break;
			}
		} while (input != 3);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}
	void readData() {
		String line = null;
		String [] temp = new String[2];
		try {
			FileReader fileReader = new FileReader("Score.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				temp = line.split(" ");
				names.add(temp[0]);
				scores.add(Integer.parseInt(temp[1]));
			}
			counter = scores.size();
			System.out.println(counter);
			bufferedReader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void writeData() {
		try {
			File file = new File ("Score.txt");
			PrintWriter pw = new PrintWriter(file);
			if (!file.exists()) {
				file.createNewFile();
			}
			int temp = 0;
			String tempName = "";
			for (int i=0; i<counter; i++) {
				for (int j=0; j<counter; j++) {
					if(scores.get(i)>scores.get(j)) {
						temp = scores.get(i);
						tempName = names.get(i);
						scores.set(i, scores.get(j));
						names.set(i, names.get(j));
						scores.set(j, temp);
						names.set(j, tempName);
					}
				}
			}
			for (int i=0; i<counter; i++) {
				pw.println(names.get(i) + " " + scores.get(i));
				//System.out.println(names.get(i) + " " + scores.get(i) + " saved");
			}
			pw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
