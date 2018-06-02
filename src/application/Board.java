package application;

import java.util.Random;
import java.util.Vector;


public class Board {
	final static int SIZE=10;
	int[][] board = new int[SIZE][SIZE];
	Vector<Position> mines = new Vector<Position>(SIZE);

	Board(int i,int j){
		Random rand = new Random();

		while(mines.size()<SIZE){
			int x = (int) rand.nextInt(SIZE);
			int y = (int) rand.nextInt(SIZE);
			Position new_pos = new Position(x,y);
			if((x != i || y != j) && !new_pos.isIn(mines)) {
				mines.add(new_pos);
			}

		}
		for (int k=0;k<SIZE;k++){
			Position temp = mines.get(k);
			board[temp.i][temp.j] = SIZE;
		}

	}
	void print_board(){
		for(int i=0;i<SIZE;i++){
			System.out.println();
			for(int j=0;j<SIZE;j++){
				if(board[i][j] == SIZE) System.out.print("* ");
				else System.out.print("o ");
			}
		}
	}
	
	int countMines(Position pos){
		int count=0;

		for (Position ngh: pos.nhbors()){
			if(ngh.isIn(mines)) {
				count++;
			}
		}
		return count;
	}
	Vector<Position> fluidFill(Position pos){
		Vector<Position> stack = new Vector<Position>(82);
		Vector<Position> opened = new Vector<Position>(82);
		stack.add(pos);
		
		while(!stack.isEmpty()){
			pos=stack.get(0);
			opened.add(pos);
			for(Position ngh: pos.nhbors()){
				if(!ngh.isIn(stack) && !ngh.isIn(opened) && countMines(ngh)==0) stack.add(ngh);
			}
			stack.remove(pos);
		}
		return opened;
	}
	Vector<Position> border(Vector<Position> component){
		Vector<Position> border = new Vector<Position>(82);
		for(Position pos: component){
			for(Position ngh: pos.nhbors()){
				if(!ngh.isIn(border) && !ngh.isIn(component)) border.add(ngh);
			}
		}
		return border;
	}
}
