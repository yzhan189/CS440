package maze;
import static java.lang.System.out;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.Vector;

import heuristic.Dist;
/* 
 * The maze object is stored in a linked list.
 * 
 * @variable root: the starting square of the maze
 * @variable width
 * @variable height
 * 
 * */

public class Maze {
	
	Node root;//starting point
	
	int width;
	int height;
	
	int dotsNum = 0;  //number of dots inside a maze

	public Node getRoot() { return root; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getDotsNum() { return dotsNum;}

	/* public using */
	public Vector<Node> goals;

	/* ===============================================
	 * Following codes are for private implementation
	 * DO NOT USE
	 * ===============================================
	 * */	
	
	Node[][] maze; 
	
	/* Deal with unexpected \r character*/
	boolean has_r = false;
	
	public Maze() {}
	
	public Node getMazeSquare(int i, int j) { return maze[i][j]; } //get node at a location
	
	/* Constructor: to construct a maze object according to maze file
	 * @param fileName: the maze file*/
	public Maze(String mazeName) throws IOException{
		
		maze = new Node[100][100];
		
		goals = new Vector<Node>();
		
		String filePath = "./mazefile/"+mazeName+".txt";
		
		int row = 0;
		int col = 0;

        FileReader inputStream = null;

        try {
            inputStream = new FileReader(filePath);
            int c;
            Node temp;
            while ((c = inputStream.read()) != -1) {
                char ch = (char) c;

                /* Deal with unexpected \r character*/
                if (ch=='\r') {
                    has_r = true;
                    continue;
                }

                /* read a new line then increment row number
            	 * set col to beginning
           		 */
                if (ch=='\n') {
                    row++;
                    if (width==0) width = col;
                    col = 0;
                    continue;
                }
                temp = new Node(row,col,ch);
                maze[row][col] = temp;

                /* set root to the starting point*/
                if (ch=='P') {
                    // if(root==null) out.println("root not setup");
                    root = temp;
                }else if (ch=='.') {
                    goals.add(temp);
                    dotsNum++;
                }
                col++;
            }
            
            height = row;
            setupPointer(root);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
	
	}
		
	/* print out current maze*/
	public void printMaze(){
		
		for (int i=0; i<height;i++) {
			
			for(int j=0;j<width;j++) {	
				
				out.print(maze[i][j].type);
			}
			out.println();
		}
	}
	
	/* adj matrix, used by MST*/
	public int[][] constructAdjMatrix(Set<Node> remainGoals, char mode) {
		int remainNum = remainGoals.size();
		int[][] ret = new int[remainNum][remainNum];
		
		int i = 0;
		int j = 0;
		//System.out.println("the size of matrix is "+remainNum);
		if (mode=='m') {
	 
			 for(Node inode:remainGoals) {	 				 
				 for(Node jnode:remainGoals) {
					 if(i<j) {
						 ret[i][j] = Dist.mahatton(inode, jnode);
					 }else if(i>j){
						 ret[i][j] = ret[j][i];
					 }else {
						 ret[i][j] = 0;
					 }
					 j++;
				 }
				 i++;
				 j = 0;
			 }
		}else if(mode=='a'){

			 for(Node inode:remainGoals) {	 				 
				 for(Node jnode:remainGoals) {
					 if(i<j) {
						 ret[i][j] = Dist.aStar(inode, jnode);
					 }else if(i>j){
						 ret[i][j] = ret[j][i];
					 }else {
						 ret[i][j] = 0;
					 }
					 j++;
				 }
				 i++;
				 j = 0;
			 }
		}
		return ret;
	}

	/* write the maze into output file
	 * @param newfileName, the output file name */
	public void outputMaze(String mazeName) throws IOException {
		
		String filePath = "./solution/"+mazeName+".out";
	    File file = new File(filePath);

	    if( file.exists() ) { file.delete(); }
	    
	    if (file.createNewFile()){
	        System.out.println("File is created!");
	    }else{
	        System.out.println("File already exists.");
	    }
	      
	    BufferedWriter bw = null;
		FileWriter fw = null;
		out.println(height);
		out.println(width);
		
		try {

			fw = new FileWriter(filePath);
			bw = new BufferedWriter(fw);
			
			for (int i=0; i<height;i++) {
				for(int j=0;j<width;j++) {
					bw.write(maze[i][j].type);
				}
				
				/* Deal with unexpected \r character*/
				if (has_r) bw.write('\r');
				bw.write('\n');
			}

			System.out.println("Done"); 

		} finally {
			if (bw != null)
				bw.close();
			if (fw != null)
				fw.close();
		}
	}
	
	/* set up NEWS pointer for each node recursively
	 * (called in constructor)
	 * */
	void setupPointer(Node root) {		
		
		if (root==null || root.isSet)
			return;
		
		int row = root.row;
		int col = root.col;
		
		root.isSet = true;
		
		if (col>0) 
			root.w = maze[row][col-1];
		if (col+1<width) 
			root.e = maze[row][col+1];
		if (row>0) 
			root.n = maze[row-1][col];
		if (row+1<height) 
			root.s = maze[row+1][col];
		
		setupPointer(root.w);
		setupPointer(root.e);
		setupPointer(root.n);
		setupPointer(root.s);
	}
}
