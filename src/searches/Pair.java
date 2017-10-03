package searches;

import maze.Node;

public class Pair <T, K> implements Comparable<Pair<T, K>> {

    private final T left;
    private final K right;

    public Pair(T left, K right){
        this.left = left;this.right = right;
    }

    public T getLeft() {
            return left;
        }

    public K getRight() {
            return right;
        }

    public boolean equals(Pair p){
            return this.left.equals(p.getLeft()) && this.right.equals(p.getRight());
        }


    @Override
    public int compareTo(Pair<T, K> o) {
        State s1 = (State) this.left;
        State s2 = (State) o.left;
        return s1.h+((String)this.right).length()-(s2.h+((String)o.right).length());
    }
}
