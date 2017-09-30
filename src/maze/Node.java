package maze;

import java.util.Comparator;

/* 
 * Each node represents a square in maze.
 * 
 * @variable type: the character printed.
 * */


public class Node implements Comparable<Node>{
	
	int row;
	int col;
	public int h;
	/* 
	 * "%" for wall
	 * " " for route
	 * "." for dot
	 * "P" for starting point (the root)
	 * */
	char type;
	
	Node n,s,e,w;

	/* These are public getter functions.*/
	
	public Node getNorth(){return n;}
	public Node getSouth(){return s;}
	public Node getWest(){return w;}
	public Node getEast(){return e;}
	
	public int getRow(){return row;}
	public int getCol(){return col;}
	public char getType(){return type;}
	
	@Override
    public int compareTo(Node y){
        if (this.h < y.h) return -1;
        if (this.h > y.h) return 1;
        return 0;
    }
	
	/* ===============================================
	 * Following codes are for private implementation
	 * DO NOT USE
	 * ===============================================
	 * */
	boolean isSet = false; // is pointer for this node is set up	
	public Node(int row, int col, char type) {		
		this.row = row;
		this.col = col;
		this.type = type;
	}
	/* Use ONLY to store solution*/
	public void setType(char c) { type=c; }

	public void printNode() {
		System.out.println("row: "+row+" "+"col: "+col);
	}
}
