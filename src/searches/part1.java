package searches;

import maze.Maze;
import maze.Node;

import java.io.IOException;
import java.util.*;

import static heuristic.MST.*;

public class part1 {

    public static int nodeExpended = 0;
    public static String bfs(Node root, Set<Node> goalNodes){
        Queue<Pair<State, String>> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        queue.add(new Pair<>(new State(root, goalNodes, 0),""));
        int count = 0;
        while(!queue.isEmpty()){
            Pair<State, String> current = queue.poll();
            State state = current.getLeft();
            String steps = current.getRight();
            Node node = state.currentPosition;
            if(visited.contains(state) || node.getType()=='%'){
                continue;
            }
            if(node.getType()=='.' && goalNodes.contains(node)){
                goalNodes.remove(node);
                nodeExpended+=count;
                return steps;
            }
            count++;
            visited.add(state);
            queue.add(new Pair<>(new State(node.getNorth(), goalNodes, 0), steps+"n"));
            queue.add(new Pair<>(new State(node.getSouth(), goalNodes, 0), steps+"s"));
            queue.add(new Pair<>(new State(node.getWest(), goalNodes, 0), steps+"w"));
            queue.add(new Pair<>(new State(node.getEast(), goalNodes, 0), steps+"e"));

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

    public static String gbfs(Node root, Set<Node> goalNodes){
    	Comparator<GPair> comparator = new GPairComparator();
        PriorityQueue<GPair<State, String>> queue = new PriorityQueue<GPair<State, String>>(2000, comparator);
        Set<Node> visited = new HashSet<>();
        queue.add(new GPair<>(new State(root, goalNodes, 0),""));
        while(!queue.isEmpty()){
            GPair<State, String> current = queue.poll();
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
                queue.add(new GPair<>(new State(node.getNorth(), goalNodes, 0), steps+"n"));
                queue.add(new GPair<>(new State(node.getSouth(), goalNodes, 0), steps+"s"));
                queue.add(new GPair<>(new State(node.getWest(), goalNodes, 0), steps+"w"));
                queue.add(new GPair<>(new State(node.getEast(), goalNodes, 0), steps+"e"));
            }
        }
        return "";
    }

    public static String ass(Node root, Set<Node> goalNodes){
    	Comparator<Pair> comparator = new PairComparator();
        PriorityQueue<Pair<State, String>> queue = new PriorityQueue<>(2000, comparator);
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
    
    public static void setH(Maze inputMaze){
		int height = inputMaze.getHeight();
		int width = inputMaze.getWidth();
		Node goalNode = inputMaze.goals.firstElement();
		for (int i=0; i<height;i++){
			for(int j=0;j<width;j++){
				Node currentNode = inputMaze.getMazeSquare(i, j);
				//calculate heuristic value based on manhattan
				currentNode.h = Math.abs(goalNode.getCol()-currentNode.getCol())+Math.abs(goalNode.getRow()-currentNode.getRow());
			}
		}
	}


    public static String ass12(Node root, Set<Node> goalNodes, Maze m){
        Comparator<Pair> comparator = new PairComparator(); // comparator to compare states based on h and cost
        PriorityQueue<Pair<State, String>> queue = new PriorityQueue<>(2000, comparator);

        Set<State> explored = new HashSet<>();  // store explored states

        int MST_from_this_state = MSTValue(goalNodes,m,'a');// calculate minimum spanning tree
        
        // add the root node											/*===change====*/
        queue.add(new Pair<>(new State(root, goalNodes, getH12(root, goalNodes, MST_from_this_state),MST_from_this_state),""));
        int count=0; // keep track of number of node expanded
        while(!queue.isEmpty()){
            boolean updateMST = false;
        	
            Pair<State, String> current = queue.poll();  // get the state in queue with lowest h+cost
            String steps = current.getRight();
            State state = current.getLeft();

            Node currentNode = state.currentPosition;
            Set<Node> goals = state.goalNodes;
            MST_from_this_state = state.mst;

            if(explored.contains(state)) // skip repeated state
                continue;

            if(goals.contains(currentNode)){
                goals.remove(currentNode);  //remove reached goal from goal set
                /* reach a goal, set is changing, 
                 * need to recalculate mst next state  */
                updateMST = true;  
                
                if(goals.isEmpty()){  // all goals are reached, print out nodes expended and return solution to main
                    System.out.println(count);
                    return steps;
                }
            }
            count++;
            explored.add(state);  //add current state to explored state

            Node next;
            int nextMST= MST_from_this_state;
            if(updateMST)
                nextMST = MSTValue(goals,m,'a');

            next = currentNode.getNorth();
            if(next.getType()!='%') {  //check if the successor states is valid before adding them to queue
                queue.add(new Pair<>(new State(next,  new HashSet<>(goals),getH12(next, goals, nextMST),nextMST), steps + "n"));
            }
            next = currentNode.getSouth();
            if(next.getType()!='%') {
                queue.add(new Pair<>(new State(next,  new HashSet<>(goals),getH12(next, goals, nextMST),nextMST), steps + "s"));
            }
            next = currentNode.getEast();
            if(next.getType()!='%') {
                queue.add(new Pair<>(new State(next,  new HashSet<>(goals),getH12(next, goals, nextMST),nextMST), steps + "e"));
            }
            next = currentNode.getWest();
            if(next.getType()!='%') {
                queue.add(new Pair<>(new State(next,  new HashSet<>(goals),getH12(next, goals, nextMST),nextMST), steps + "w"));
            }
        }
        return "";
    }

    public static String part1EC(Node startNode, Set<Node> goals){
        int remain = goals.size();
        String solution = "";
        Node node = startNode;
        int count=0;
        while(remain>0){ //repeatedly use bfs to find the closest dot in maze until none is remaining
            String subSolution = bfs(node, goals);
            solution += subSolution;
            remain--;
            while(!subSolution.isEmpty()) {  //get current location based on subsolution
                switch (subSolution.charAt(0)) {
                    case 'n':
                        node = node.getNorth();
                        break;
                    case 's':
                        node = node.getSouth();
                        break;
                    case 'w':
                        node = node.getWest();
                        break;
                    case 'e':
                        node = node.getEast();
                        break;
                }
                subSolution = subSolution.substring(1);
            }
        }
        return solution;
    }

    public static int getH12(Node currentNode, Set<Node> goalNodes, int mstValue){
    		return mstValue+closetGoalDistance( goalNodes, currentNode);
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

            Maze m = new Maze("mediumSearch");

//            String solution =ass12(m.getRoot(),getGoalNodes(m),m);
            String solution  = part1EC(m.getRoot(), getGoalNodes(m));
            Node node = m.getRoot();
            System.out.println("Cost: " + solution.length());
            System.out.println("node expended: " + nodeExpended);
            System.out.println(solution);
            char c='0';
            while(!solution.isEmpty()){  //print out solution based on returned string
                switch (solution.charAt(0)){
                    case 'n':
                        node = node.getNorth();
                        break;
                    case 's':
                        node = node.getSouth();
                        break;
                    case 'w':
                        node = node.getWest();
                        break;
                    case 'e':
                        node = node.getEast();
                        break;
                }
                if(node.getType()=='.') {
                    if(c=='9')  //switch to letter when ran out of numbers
                        c='a';
                    else if(c=='z') //switch to upper letter when ran out of lowercase letters
                        c='A';
                    else
                        c++;
                }
//                else{
//                    node.setType('.');
//                }
//                node.setType('x');
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

    }
}
