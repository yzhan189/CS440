package heuristic;

//A Java program for Prim's Minimum Spanning Tree (MST) algorithm.
//The program is for adjacency matrix representation of the graph

import java.util.*;

import maze.Maze;
import maze.Node;

import java.lang.*;
import java.io.*;

/* Usage:
 * 		int MSTValue(Vector<Node> remainGoals, Maze maze, char mode);
 * 	
 * 		Given a heuristic value by calculating the sum of MST edges of remaining goals
 * 		
 * 
 * @param remainGoals: the vector that contains the undiscovered goals.
 * @param maze: the maze obj you are currently searching
 * @param mode: 	'm' : use Manhattan distance to calculate the distance between each two nodes
 * 				'a' : use A* to calculate the distance between each two nodes
 *
 * 
 * 		closetGoalDistance(Vector<Node> remainGoals,Node currentPosition)
 * 
 *  the closet distance between curr position and one of the goal
 * 	using astar here
 * 
 * */

public class MST
{
	public static int MSTValue(Set<Node> remainGoals, Maze maze, char mode) {
		int[][] graph = maze.constructAdjMatrix(remainGoals,'a');
		MST t = new MST(remainGoals.size());
		return t.sumMST(graph);
	}
	
	 public static int closetGoalDistance(Set<Node> remainGoals,Node currentPosition) {
		 // stores the closet distance between current position and one of the goal
		 if(currentPosition.getType()=='.') return 0;
		 
		 int min = Integer.MAX_VALUE;
		 int temp;
		 Iterator<Node> it = remainGoals.iterator();
		 while(it.hasNext()) {
			 temp = Dist.aStar(it.next(),currentPosition);
			 if (temp<min) min = temp;
		 }

		 return min;
	 }
	
	
	
	
	
//===================  Implementation   Below  =========================	
	

	
 // Number of vertices in the graph
 int V;
 
 public MST(int numOfVertices) {
	 V = numOfVertices;
 }

 // A utility function to find the vertex with minimum key
 // value, from the set of vertices not yet included in MST
 int minKey(int key[], Boolean mstSet[])
 {
     // Initialize min value
     int min = Integer.MAX_VALUE, min_index=-1;

     for (int v = 0; v < V; v++)
         if (mstSet[v] == false && key[v] < min)
         {
             min = key[v];
             min_index = v;
         }

     return min_index;
 }


 
 
 public int sumMST(int graph[][])
 {
	 
     // Array to store constructed MST
     int parent[] = new int[V];

     // Key values used to pick minimum weight edge in cut
     int key[] = new int [V];

     // To represent set of vertices not yet included in MST
     Boolean mstSet[] = new Boolean[V];

     // Initialize all keys as INFINITE
     for (int i = 0; i < V; i++)
     {
         key[i] = Integer.MAX_VALUE;
         mstSet[i] = false;
     }

     // Always include first 1st vertex in MST.
     key[0] = 0;     // Make key 0 so that this vertex is
                     // picked as first vertex
     parent[0] = -1; // First node is always root of MST

     // The MST will have V vertices
     for (int count = 0; count < V-1; count++)
     {
         // Pick thd minimum key vertex from the set of vertices
         // not yet included in MST
         int u = minKey(key, mstSet);

         // Add the picked vertex to the MST Set
         mstSet[u] = true;

         // Update key value and parent index of the adjacent
         // vertices of the picked vertex. Consider only those
         // vertices which are not yet included in MST
         for (int v = 0; v < V; v++)

             // graph[u][v] is non zero only for adjacent vertices of m
             // mstSet[v] is false for vertices not yet included in MST
             // Update the key only if graph[u][v] is smaller than key[v]
             if (graph[u][v]!=0 && mstSet[v] == false &&
                 graph[u][v] <  key[v])
             {
                 parent[v]  = u;
                 key[v] = graph[u][v];
             }
     }

     // print the constructed MST
     // printMST(parent, V, graph);
     
     //System.out.println("sum ");
     int sum = 0;
     for (int i = 1; i < V; i++)
        sum += graph[i][parent[i]];
     return sum;
 }
 
 
 
 
 
 
 
 
 
 
 //======= copy ========
 
 
 
 
 
 
 
 
 
 
 
 
 // A utility function to print the constructed MST stored in
 // parent[]
 void printMST(int parent[], int n, int graph[][])
 {
     System.out.println("Edge   Weight");
     for (int i = 1; i < V; i++)
         System.out.println(parent[i]+" - "+ i+"    "+
                            graph[i][parent[i]]);
 }

 // Function to construct and print MST for a graph represented
 //  using adjacency matrix representation
 public void primMST(int graph[][])
 {
	 sumMST(graph);
     
 }

 
 

 
 
// public static void main (String[] args)
// {
//     /* Let us create the following graph
//        2    3
//     (0)--(1)--(2)
//     |    / \   |
//    6|  8/   \5 |7
//     | /      \ |
//     (3)-------(4)
//          9          */
//     MST t = new MST();
//     int graph[][] = new int[][] {{0, 2, 0, 6, 0},
//                                 {2, 0, 3, 8, 5},
//                                 {0, 3, 0, 0, 7},
//                                 {6, 8, 0, 0, 9},
//                                 {0, 5, 7, 9, 0},
//                                };
//
//     // Print the solution
//     t.primMST(graph);
// }
}
//This code is contributed by Aakash Hasija
