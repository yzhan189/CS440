package searches;

import maze.Node;

import java.util.Set;

public class State{

    public Node currentPosition;
    public Set<Node> goalNodes;
    public int h;

    public State(Node currentPosition, Set<Node> goalNodes, int h) {
        this.currentPosition = currentPosition;
        this.goalNodes = goalNodes;
        this.h=h;
    }

    @Override
    public boolean equals(Object o) {
        return this.currentPosition==((State)o).currentPosition && this.goalNodes.equals(((State)o).goalNodes);
    }

    public int hashCode() {
        return currentPosition.hashCode() ^ goalNodes.hashCode();
    }
}
