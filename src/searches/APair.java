package searches;

import java.util.Comparator;

import maze.Node;

public class APair <T, K> implements Comparable<APair<T, K>>{

    private final T left;
    private final K right;

    public APair(T left, K right){
        this.left = left;this.right = right;
    }

	public T getLeft() {
            return left;
        }

    public K getRight() {
            return right;
        }

    public boolean equals(APair p){
            return this.left.equals(p.getLeft()) && this.right.equals(p.getRight());
        }

	@Override
	public int compareTo(APair<T, K> o2) {
		// TODO Auto-generated method stub
		Node n1 = (Node)this.left;
		Node n2 = (Node)o2.left;
		return n1.h+((String)this.right).length()-(n2.h+((String)o2.right).length());
		//return n1.compareTo(n2);
	}


}
