package model_nail;
import java.util.Observable;

import controller_hammer.Point;

// Maze should inherit Observable, but Java does not allow multiple inheritance, 
// so i goes here instead. 

public abstract class Board extends Observable {		
	protected int maxRow, maxCol, maxCell;
	
	public Board( int maxRow, int maxCol ) {
		this.maxRow = maxRow;
		this.maxCol = maxCol;
		this.maxCell = maxRow*maxCol; 
	}
	
	protected int getCellId( Point p ) { 
		return p.row*maxCol + p.col; 
	}
	
	protected int getRow( int cellId ) { 
		return cellId / maxCol; 
	}
	
	protected int getCol( int cellId ) { 
		return cellId % maxCol; 
	}
	
	protected boolean isValid( Point p ) {
		return p.row >= 0 && p.row < maxRow && p.col >= 0 && p.col < maxCol;
	}
}
