package controller_hammer;

import javax.swing.JOptionPane;

import model_nail.DisjointSets;



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
    	System.out.println("Create w:"+super.maxCol+" & "+super.maxRow);

    	setChanged();
   	 	notifyObservers(super.maxCell);
    	
//    	 setChanged();
//    	 notifyObservers(maxRow,maxCol);
//    	 
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
