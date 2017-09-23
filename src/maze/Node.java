package maze;


/* 
 * Each node represents a square in maze.
 * 
 * @variable type: the character printed.
 * */


public class Node {
	
	int row;
	int col;
	
	/* 
	 * "%" for wall
	 * " " for route
	 * "." for dot
	 * "P" for starting point (the root)
	 * */
	char type;
	
	Node n,s,e,w;

	/* These are public getter functions.*/
	
	public Node getNorth() {	return n; }
	public Node getSouth() {	return s; }
	public Node getWest() {	return w; }
	public Node getEast() {	return e; }
	
	public int getRow() { return row; }
	public int getCol() { return col; }
	
	public char getType() { return type; }

	
	
	
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
	protected void setType(char c) { type=c; }
	
}
