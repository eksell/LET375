import java.util.Random;
import java.util.ArrayList;
import java.util.List;








public class Maze extends Board {
	//Observable inherited through Board
	private ExtendedGraph graph;
	private DisjointSets set;
	private int col, row, amountOfSets;
	private boolean connect;
	private Point.Direction d;
	private Random rand;
	private Random randCell;

	public Maze( int rows, int cols ) {
		super(rows,cols);
		row = rows;
		col = cols;
		randCell = new Random();
		rand = new Random(); 

		//Adds
		System.out.println("Maze Construct");
	}

	public void create() { 
		
		graph = new ExtendedGraph();
		set = new DisjointSets(maxCell);
		amountOfSets = maxCell;
		
		
		while( amountOfSets > 1){
			
			
			//Picking a random direction
			int i;
		
				i = rand.nextInt(4);
				if	(i == 0){	
					d = Point.Direction.UP ;
				}
				
				if	(i == 1){ 	
					d = Point.Direction.LEFT;
				}
				
				if(i == 2) {	
					d = Point.Direction.RIGHT;
				}
				
				else   { 	
					d = Point.Direction.DOWN;
				}
				
				//Creating two points on the same random cell inside grid
				
				
				int a = randCell.nextInt(maxCell);
				
				Point currentPoint = new Point(getRow(a),getCol(a)); 
				Point newPoint = new Point(getRow(a),getCol(a));
				
				
				
				//Moves newPoint from currentPoint in the random direction determined above   
				newPoint.move(d);
				
				//Check if newPoint is still inside the original grid, if so, check if cells are already 
				//connected or if we should do a new union
				if(isValid(newPoint))
				{
				
			
			
			int currentId = getCellId(currentPoint);
			int newId = getCellId(newPoint);
			int set1 = set.find(currentId);
			int set2 = set.find(newId);
			
			if(set1 != set2){
				set.union(set1, set2);
				amountOfSets--;
				
				//Store the edges in both directions in graph
				graph.addEdge(currentId, newId, 1);
				graph.addEdge(newId, currentId, 1);
				
				Pair<Integer, Point.Direction> pair = new Pair<Integer, Point.Direction>(currentId, d);
    			setChanged();
    			notifyObservers(pair);
			}
			
		}	
			
			
	}

			
			
			
			
			
}
		
	
	
	public void search() {
		
		//Get shortest path from upper left corner cell to lower right corner cell
    	List<Integer> path = graph.getPath(maxCell - 1);
    	
    	for( Integer cellId : path ) {
        	setChanged();
        	notifyObservers(cellId);
    	}
    }


}

