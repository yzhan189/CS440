package searches;
import java.util.Comparator;

import maze.Node;

public class GPair <T, K> implements Comparable<GPair<T, K>>{

    private final T left;
    private final K right;

    public GPair(T left, K right){
        this.left = left;this.right = right;
    }

	public T getLeft() {
            return left;
        }

    public K getRight() {
            return right;
        }

    public boolean equals(GPair p){
            return this.left.equals(p.getLeft()) && this.right.equals(p.getRight());
        }

	@Override
	public int compareTo(GPair<T, K> o1) {
		// TODO Auto-generated method stub
		Node n1 = (Node)this.left;
		Node n2 = (Node)o1.left;
		//return n1.h+((String)this.right).length()-(n2.h+((String)o1.right).length());
		return n1.compareTo(n2);
	}


}