package searches;

import java.util.Comparator;

public class PairComparator implements Comparator<Pair> {
    @Override
    public int compare(Pair o1, Pair o2) {
        State s1 = (State)o1.getLeft();
        State s2 = (State)o2.getLeft();
        return (s1.h + ((String) o1.getRight()).length()) - (s2.h + ((String) o2.getRight()).length());
    }
}
