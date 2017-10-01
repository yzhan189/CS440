package searches;

import maze.Maze;
import maze.Node;

import java.io.IOException;
import java.util.*;

import static heuristic.MST.*;

public class part1 {

    public static String bfs(Node root, Set<Node> goalNodes){
        Queue<Pair<State, String>> queue = new LinkedList<>();

        Set<Node> visited = new HashSet<>();

        queue.add(new Pair<>(new State(root, goalNodes, 0),""));

        while(!queue.isEmpty()){
            Pair<State, String> current = queue.poll();
            Node node = current.getLeft().currentPosition;
            String steps = current.getRight();
            if(visited.contains(node)){
                continue;
            }
            if(node.getType()=='.'){
                return steps;
            }
            if(node.getType() ==' ' || node.getType()=='P') {
                visited.add(current.getLeft().currentPosition);
                queue.add(new Pair<>(new State(node.getNorth(), goalNodes, 0), steps+"n"));
                queue.add(new Pair<>(new State(node.getSouth(), goalNodes, 0), steps+"s"));
                queue.add(new Pair<>(new State(node.getWest(), goalNodes, 0), steps+"w"));
                queue.add(new Pair<>(new State(node.getEast(), goalNodes, 0), steps+"e"));
            }
        }
        return "";
    }

    public static String dfs(Node root, Set<Node> goalNodes){
        Stack<Pair<State, String>> stack = new Stack<>();

        Set<Node> visited = new HashSet<>();

        stack.add(new Pair<>(new State(root, goalNodes, 0),""));

        while(!stack.isEmpty()){
            Pair<State, String> current = stack.pop();
            Node node = current.getLeft().currentPosition;
            String steps = current.getRight();
            if(visited.contains(node)){
                continue;
            }
            if(node.getType()=='.'){
                return steps;
            }
            if(node.getType() ==' ' || node.getType()=='P') {
                visited.add(current.getLeft().currentPosition);
                stack.push(new Pair<>(new State(node.getNorth(), goalNodes, 0), steps+"n"));
                stack.push(new Pair<>(new State(node.getSouth(), goalNodes, 0), steps+"s"));
                stack.push(new Pair<>(new State(node.getWest(), goalNodes, 0), steps+"w"));
                stack.push(new Pair<>(new State(node.getEast(), goalNodes, 0), steps+"e"));
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


    public static String ass12(Node root, Set<Node> goalNodes, Maze m){
        Comparator<Pair> comparator = new PairComparator();
        PriorityQueue<Pair<State, String>> queue = new PriorityQueue<>(2000, comparator);

        Set<State> explored = new HashSet<>();
        
        
        /*===change====*/
        int MST_from_this_state = MSTValue(goalNodes,m,'m');
        
        // add the root node											/*===change====*/
        queue.add(new Pair<>(new State(root, goalNodes, getH12(root, goalNodes, MST_from_this_state),MST_from_this_state),""));
        int count=0;
        
        while(!queue.isEmpty()){
        	
        		/*===change====*/
        		boolean updateMST = false;
    	
        	
            Pair<State, String> current = queue.poll();
            String steps = current.getRight();
            State state = current.getLeft();
//            System.out.println(state.h+steps.length()+" ");

            Node currentNode = state.currentPosition;
            Set<Node> goals = state.goalNodes;

            /*===change====*/
            MST_from_this_state = state.mst;
            
            if(explored.contains(state))
                continue;

            if(goals.contains(currentNode)){
                goals.remove(currentNode);
                
                /* reach a goal, set is changing, 
                 * need to recalculate mst next state  */
                updateMST = true;  
                
                if(goals.isEmpty()){
                    System.out.println(count);
                    return steps;
                }
            }
            count++;
//            System.out.println(steps);

            explored.add(state);

            int MST_val = MST_from_this_state;
            if(updateMST){
                MST_val = MSTValue(goals,m,'m');
            }

            if(currentNode.getNorth().getType()!='%') {
                queue.add(new Pair<>(new State(currentNode.getNorth(), new HashSet<>(goals),getH12(currentNode.getNorth(), goals, MST_val),MST_val),steps + "n"));
            }
            if(currentNode.getSouth().getType()!='%') {
                queue.add(new Pair<>(new State(currentNode.getSouth(),  new HashSet<>(goals),getH12(currentNode.getSouth(), goals, MST_val),MST_val), steps + "s"));
            }
            if(currentNode.getEast().getType()!='%') {
                queue.add(new Pair<>(new State(currentNode.getEast(),  new HashSet<>(goals),getH12(currentNode.getEast(), goals, MST_val),MST_val), steps + "e"));
            }
            if(currentNode.getWest().getType()!='%') {
                queue.add(new Pair<>(new State(currentNode.getWest(),  new HashSet<>(goals),getH12(currentNode.getWest(), goals, MST_val),MST_val), steps + "w"));
            }


        }
        return "";
    }
    

    public static int getH12(Node currentNode, Set<Node> goalNodes, int mstValue){
    		return mstValue+
				closetGoalDistance( goalNodes, currentNode);
  }

    public static String bfs12(Node root, Node goal){
        Queue<Pair<Node, String>> queue = new LinkedList<>();

        Set<Node> visited = new HashSet<>();

        queue.add(new Pair<>(root,""));

        while(!queue.isEmpty()){
            Pair<Node, String> current = queue.poll();
            Node node = current.getLeft();
            String steps = current.getRight();
            if(visited.contains(node) || node.getType() =='%'){
                continue;
            }
            if(node==goal){
                return steps;
            }

            visited.add(current.getLeft());
            queue.add(new Pair<>(node.getNorth(), steps+"n"));
            queue.add(new Pair<>(node.getSouth(), steps+"s"));
            queue.add(new Pair<>(node.getWest(), steps+"w"));
            queue.add(new Pair<>(node.getEast(), steps+"e"));

        }
        return "";
    }

    public static Set<Node> getGoalNodes(Maze m){
        Set<Node> goalNode = new HashSet<>();
        for (int i=0; i<m.getHeight();i++){
            for(int j=0;j<m.getWidth();j++){
                Node currentNode = m.getMazeSquare(i, j);
                if(currentNode.getType() == '.'){
                    goalNode.add(currentNode);
                }
            }
        }
        return goalNode;
    }
    
    public static void main(String[] args){
    	
    	long start_time = System.currentTimeMillis();

    	try {
            Maze m = new Maze("tinySearch");
            String solution =ass12(m.getRoot(), getGoalNodes(m), m);
            Node node = m.getRoot();
            System.out.println("Cost: " + solution.length());
            System.out.println(solution);
            char c='a';
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
                if(node.getType()=='.') {
                    node.setType(c);
                    c++;
                }else if(node.getType()==' '){
                    node.setType('-');
                }
                solution = solution.substring(1);
            }
            m.printMaze();

        } catch (IOException e){
            System.out.print("Oops something wrong with Maze constructor");
            return;
        }

    		long end_time = System.currentTimeMillis();
    		double difference = (end_time - start_time) / 1e3;
    		System.out.println("Time elapsed: "+difference+" s");
//
//        Comparator<Pair> comparator = new PairComparator();
//        PriorityQueue<Pair<State, String>> queue = new PriorityQueue<>(2000, comparator);
//        queue.add(new Pair<State, String>(new State(null, null, 5), ""));
//        queue.add(new Pair<State, String>(new State(null, null, 4), ""));
//        queue.add(new Pair<State, String>(new State(null, null, 10), ""));
//        queue.add(new Pair<State, String>(new State(null, null, 4), ""));
//        queue.add(new Pair<State, String>(new State(null, null, 1), ""));
//        queue.add(new Pair<State, String>(new State(null, null, 6), ""));
//
//        Pair<State, String > t1 = queue.poll();
//        Pair<State, String > t2 = queue.poll();
//        Pair<State, String > t3 = queue.poll();
//        Pair<State, String > t4 = queue.poll();
//        Pair<State, String > t5 = queue.poll();
//        Pair<State, String > t6 = queue.poll();
    }
}
