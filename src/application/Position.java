package application;
import java.util.Vector;


public class Position {
	int i;
	int j;
	
	Position(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	Vector<Position> nhbors (){
		Vector<Position> nhbors = new Vector<Position>(8); 		
		for(int k=-1;k<2;k++){
			for(int l=-1;l<2;l++){
				if(k==0 && l==0) continue;
				if((new Position(i+k,j+l)).isValid()) nhbors.add(new Position(i+k,j+l)); 
			}
		}
		
		return nhbors;
	}
	Vector<Position> nhbors1(){
		Vector<Position> nhbors1 = new Vector<Position>(4);
		if((new Position(i,j-1)).isValid()) nhbors1.add(new Position(i,j-1));
		if((new Position(i-1,j)).isValid()) nhbors1.add(new Position(i-1,j)); 
		if((new Position(i,j+1)).isValid()) nhbors1.add(new Position(i,j+1)); 
		if((new Position(i+1,j)).isValid()) nhbors1.add(new Position(i+1,j));
		return nhbors1;
	}

	public String toString(){
		String s =("i: " + i +" j:" + j);
		return s;
	}
	boolean equalsTo(Position pos){
		if(pos.i == this.i && pos.j == this.j) return true;
		return false;
	}
	boolean isIn(Vector<Position> vect){
		for(Position pos: vect) if(this.equalsTo(pos)) return true;
		return false;
	}
	boolean isValid() {
		return (i>=0 && j>=0 && i<Board.SIZE && j<Board.SIZE);
	}
	
}

