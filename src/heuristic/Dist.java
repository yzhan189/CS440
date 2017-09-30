package heuristic;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import maze.Node;
import searches.APair;
import searches.APairComparator;

public class Dist {
	
	public static int mahatton(Node a, Node b) {
		return (Math.abs(a.getRow()-b.getRow())+Math.abs(a.getCol()-b.getCol()));
	}
	
	/* find true distance between root and destination using A* */
	public static int aStar(Node root, Node dest) {
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
            if(node==dest){// find destination
                return steps.length();
            }
            if(node.getType() !='%' ) { // if not wall
                visited.add(current.getLeft());
                queue.add(new APair<>(node.getNorth(), steps+"n"));
                queue.add(new APair<>(node.getSouth(), steps+"s"));
                queue.add(new APair<>(node.getWest(), steps+"w"));
                queue.add(new APair<>(node.getEast(), steps+"e"));
            }
        }
        return 0;
	}

}
