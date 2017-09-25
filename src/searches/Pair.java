package searches;

public class Pair <T, K> {

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


}
