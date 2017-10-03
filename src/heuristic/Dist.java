package heuristic;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import maze.Node;
import searches.Pair;
import searches.PairComparator;

public class Dist {
	
	public static int mahatton(Node a, Node b) {
		return (Math.abs(a.getRow()-b.getRow())+Math.abs(a.getCol()-b.getCol()));
	}
	
	/* find true distance between root and destination using A* */
	public static int aStar(Node root, Node dest) {
		Comparator<Pair> comparator = new PairComparator();
        PriorityQueue<Pair<Node, String>> queue = new PriorityQueue<Pair<Node, String>>(2000, comparator);//PriorityQueue<Pair<Node, String>>();

        Set<Node> visited = new HashSet<>();

        queue.add(new Pair<>(root,""));

        while(!queue.isEmpty()){
            Pair<Node, String> current = queue.poll();
            Node node = current.getLeft();
            String steps = current.getRight();
            if(visited.contains(node)){
                continue;
            }
            if(node==dest){// find destination
                return steps.length();
            }
            if(node.getType() !='%' ) { // if not wall
                visited.add(current.getLeft());
                queue.add(new Pair<>(node.getNorth(), steps+"n"));
                queue.add(new Pair<>(node.getSouth(), steps+"s"));
                queue.add(new Pair<>(node.getWest(), steps+"w"));
                queue.add(new Pair<>(node.getEast(), steps+"e"));
            }
        }
        return 0;
	}

}
