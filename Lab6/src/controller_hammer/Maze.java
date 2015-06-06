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

	public Maze( int rows, int cols ) {
		super(rows,cols);
		set = new DisjointSets(rows*cols);
		graph = new ExtendedGraph(rows, cols);
		//Adds
		System.out.println("Maze Construct");
	}

	public void create() { //TODO Create the maze

		//Added, testing relation to boarddisplay
		System.out.println("Create:"+super.maxCol+"*"+super.maxRow+" maze.");

		//Notify observers of this maze-instance


		/**
		 * Har DJ-set som här loopas till att ha unioner ges riktningar
		 */

		Random rand = new Random(); // To get a rng for the direction
		List<Direction> dirList = new ArrayList<Direction>();
		dirList.add(Direction.UP);
		dirList.add(Direction.DOWN);
		dirList.add(Direction.RIGHT);
		dirList.add(Direction.LEFT);

		HashMap<Integer, Direction> mazeMap = new HashMap<Integer, Direction>();

		int wallsLeft = 0;	// walls left to knock down
		int cell = 0;
		Point currentPoint = new Point(0,0);
		boolean connect = true;


		while(cell < (maxCell-1)){
			System.out.print("W:"+cell+" of "+ maxCell);
			currentPoint = new Point(getRow(cell),getCol(cell));

			while(connect){

				Direction d = dirList.get(rand.nextInt(3)); //Random direction to move in

				if(		d == Direction.RIGHT && getCol(cell) == maxCol||
						d == Direction.LEFT && getCol(cell) == 1||
						d == Direction.DOWN && getRow(cell) == maxRow||
						d == Direction.UP && getRow(cell) == 1){
					System.out.println("cell");
					continue;
				}
				else{

					int oldPoint = cell;
					int newPoint;
					currentPoint = new Point(getRow(cell),getCol(cell));

					Point testPoint = new Point(getRow(cell),getCol(cell));
					testPoint.move(d);

					if(0 <= getCellId(testPoint) && getCellId(testPoint)<= (maxCell-1)){
						currentPoint.move(d);
						newPoint = getCellId(currentPoint);
						System.out.print("["+oldPoint+"->"+newPoint+"] ");
					}else continue;

					if(set.find(newPoint) != set.find(oldPoint)){
						System.out.print("C:"+cell+" "+d+"\n");
						mazeMap.put(getCellId(currentPoint), d); // Connect position and direction
						set.union(set.find(oldPoint),set.find(newPoint));
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

			}

		}

		setChanged();
		notifyObservers(mazeMap);

		//    	 int knockedWalls = 0;
		//    	 //while(knockedWalls < (maxCell-1)();//TODO
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
