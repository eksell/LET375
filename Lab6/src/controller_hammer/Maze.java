package controller_hammer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import model_nail.*;
import model_nail.Point.Direction;




public class Maze extends Board {
	//Observable inherited through Board
	private ExtendedGraph graph;
	private DisjointSets set;
	private int oldPoint, newPoint, col, row;

	public Maze( int rows, int cols ) {
		super(rows,cols);
		set = new DisjointSets(rows*cols);
		graph = new ExtendedGraph(rows, cols);
		row = rows;
		col = cols;
		//Adds
		System.out.println("Maze Construct");
	}

	public void create() { //TODO Create the maze

		//Added, testing relation to boarddisplay
		System.out.println("Create:"+super.maxCol+"*"+super.maxRow+" maze.");

		/** Har DJ-set som här loopas till att ha unioner ges riktningar */

		HashMap<Integer, Direction> mazeMap = new HashMap<Integer, Direction>();

		int wallsLeft = row*col;	// walls left to knock down
		int cell = 0;
		Point currentPoint = new Point(0,0);
		boolean connect = true;

		//jobba på en rekursiv fuktion som söker igenom alla grenar...

		while(wallsLeft != maxCell){	//cell >= 0 && cell <= (maxCell)){

			System.out.print("W:"+cell+" of "+ maxCell);

			oldPoint = cell;
			row = getRow(cell);
			col = getCol(cell);
			currentPoint = new Point(row,col);
			Direction d = randDir();

			currentPoint.move(d);
			newPoint = getCellId(currentPoint);
			System.out.print("["+oldPoint+"->"+newPoint+"] ");

			//				Point testPoint = new Point(getRow(cell),getCol(cell));
			//				testPoint.move(d);

			if(set.find(newPoint) != set.find(oldPoint)){
				System.out.print("C:"+cell+" "+d+"\n");
				mazeMap.put(getCellId(currentPoint), d); // Connect position and direction
				set.union(set.find(oldPoint),set.find(newPoint));
				wallsLeft--;
				cell++;
			}

			for(int i = 0; i<maxCell; i++){
				connect = false;
				if(set.find(0) != set.find(i)){
					connect = true;
					break;
				}
			}
		}

		//Notify observers of this maze-instance
		setChanged();
		notifyObservers(mazeMap);

		//    	 int knockedWalls = 0;
		//    	 //while(knockedWalls < (maxCell-1)();//TODO
	}

	/** Takes your position and return a direction that won't go outside the labyrinth */
	private Direction randDir(){
		Random rand = new Random(); // To get a rng for the direction
		Direction d;
		int i;

		// Would it be more effective to get possible values then randomize among them? 
		//Then I'd still need a list of directions and so on...?
		do{	
			i = rand.nextInt(4);
			if		(i == 0 && !(row == 0)) 	d = Direction.UP ; 
			else if	(i == 1 && !(col == 0)) 	d = Direction.LEFT;
			else if	(i == 2 && !(col == this.maxCol))d = Direction.RIGHT;
			else if	(i == 3 && !(row == this.maxRow))d = Direction.DOWN;
			else d = null;
			
		}while(d == null);

		System.out.print(d);
		return d;
	}

	public void search() { //TODO Later,  Maze search
		graph.unweighted(0);
		//	     List<Integer> list = graph.getPath(maxCell-1);
		//    	 for(Integer i : list){
		//    		 setChanged();
		//    		 notifyObservers(i);
		//    	 }
	}

	//    ...
}
