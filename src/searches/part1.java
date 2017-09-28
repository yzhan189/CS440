package searches;

import maze.Maze;
import maze.Node;

import java.io.IOException;
import java.util.*;

public class part1 {
    public static String bfs(Node root){
        Queue<Pair<Node, String>> queue = new LinkedList<>();

        Set<Node> visited = new HashSet<>();

        queue.add(new Pair<>(root,""));

        while(!queue.isEmpty()){
            Pair<Node, String> current = queue.poll();
            Node node = current.getLeft();
            String steps = current.getRight();
            if(visited.contains(node)){
                continue;
            }
            if(node.getType()=='.'){
                return steps;
            }
            if(node.getType() ==' ' || node.getType()=='P') {
                visited.add(current.getLeft());
                queue.add(new Pair<>(node.getNorth(), steps+"n"));
                queue.add(new Pair<>(node.getSouth(), steps+"s"));
                queue.add(new Pair<>(node.getWest(), steps+"w"));
                queue.add(new Pair<>(node.getEast(), steps+"e"));
            }
        }
        return "";
    }

    public static String dfs(Node root){
        Stack<Pair<Node, String>> stack = new Stack<>();

        Set<Node> visited = new HashSet<>();
        stack.add(new Pair<>(root,""));

        while(!stack.isEmpty()) {
            Pair<Node, String> current = stack.pop();
            Node node = current.getLeft();
            String steps = current.getRight();
            if(visited.contains(node)){
                continue;
            }
            if (node.getType() == '.') {
                return steps;
            }

            if(node.getType() ==' ' || node.getType()=='P') {
                visited.add(current.getLeft());
                stack.push(new Pair<>(node.getNorth(), steps+"n"));
                stack.push(new Pair<>(node.getSouth(), steps+"s"));
                stack.push(new Pair<>(node.getWest(), steps+"w"));
                stack.push(new Pair<>(node.getEast(), steps+"e"));
            }
        }
        return "";
    }

    public static String gbfs(Node root){
    	Comparator<GPair> comparator = new GPairComparator();
        PriorityQueue<GPair<Node, String>> queue = new PriorityQueue<GPair<Node, String>>(2000, comparator);//PriorityQueue<Pair<Node, String>>();

        Set<Node> visited = new HashSet<>();

        queue.add(new GPair<>(root,""));

        while(!queue.isEmpty()){
            GPair<Node, String> current = queue.poll();
            Node node = current.getLeft();
            String steps = current.getRight();
            if(visited.contains(node)){
                continue;
            }
            if(node.getType()=='.'){
                return steps;
            }
            if(node.getType() ==' ' || node.getType()=='P') {
                visited.add(current.getLeft());
                queue.add(new GPair<>(node.getNorth(), steps+"n"));
                queue.add(new GPair<>(node.getSouth(), steps+"s"));
                queue.add(new GPair<>(node.getWest(), steps+"w"));
                queue.add(new GPair<>(node.getEast(), steps+"e"));
            }
        }
        return "";
    }

    public static String ass(Node root){
    	Comparator<APair> comparator = new APairComparator();
        PriorityQueue<APair<Node, String>> queue = new PriorityQueue<APair<Node, String>>(2000, comparator);//PriorityQueue<Pair<Node, String>>();

        Set<Node> visited = new HashSet<>();

        queue.add(new APair<>(root,""));

        while(!queue.isEmpty()){
            APair<Node, String> current = queue.poll();
            Node node = current.getLeft();
            String steps = current.getRight();
            if(visited.contains(node)){
                continue;
            }
            if(node.getType()=='.'){
                return steps;
            }
            if(node.getType() ==' ' || node.getType()=='P') {
                visited.add(current.getLeft());
                queue.add(new APair<>(node.getNorth(), steps+"n"));
                queue.add(new APair<>(node.getSouth(), steps+"s"));
                queue.add(new APair<>(node.getWest(), steps+"w"));
                queue.add(new APair<>(node.getEast(), steps+"e"));
            }
        }
        return "";
    }
    
    public static void setH(Maze inputMaze){
		int height = inputMaze.getHeight();
		int width = inputMaze.getWidth();
		Node goalNode = null;
		for (int i=0; i<height;i++){
			for(int j=0;j<width;j++){
				Node currentNode = inputMaze.getMazeSquare(i, j);
				if(currentNode.getType() == '.'){
					goalNode = currentNode;
					break;
				}
			}
			if(goalNode != null) break;
		}
		for (int i=0; i<height;i++){
			for(int j=0;j<width;j++){
				Node currentNode = inputMaze.getMazeSquare(i, j);
				currentNode.h = Math.abs(goalNode.getCol()-currentNode.getCol())+Math.abs(goalNode.getRow()-currentNode.getRow());
			}
		}
	}
    
    public static void main(String[] args){
    	try {
            //Maze m = new Maze("openMaze");
            //Maze m = new Maze("mediumMaze");
            Maze m = new Maze("bigMaze");
            setH(m);
/////////////////////////////////////////////////////////////////////////////////
            //String solution = bfs(m.getRoot());
            //String solution = dfs(m.getRoot());
            //String solution = ass(m.getRoot());
            String solution = gbfs(m.getRoot());
            Node node = m.getRoot();
            System.out.println("Cost: " + solution.length());
            while(!solution.isEmpty()){
                if(solution.charAt(0)=='n'){
                    node = node.getNorth();
                }
                if(solution.charAt(0)=='s'){
                    node = node.getSouth();
                }
                if(solution.charAt(0)=='w'){
                    node = node.getWest();
                }
                if(solution.charAt(0)=='e'){
                    node = node.getEast();
                }
                node.setType('.');
                solution = solution.substring(1);
            }
            m.printMaze();

        } catch (IOException e){
            System.out.print("Oops something wrong with Maze constructor");
            return;
        }
    }
}
