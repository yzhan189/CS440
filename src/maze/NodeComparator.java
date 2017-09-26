package maze;


// Test.java
import java.util.Comparator;
import java.util.PriorityQueue;



// StringLengthComparator.java
import java.util.Comparator;

public class NodeComparator implements Comparator<Node>
{
    @Override
    public int compare(Node x, Node y)
    {
        // Assume neither string is null. Real code should
        // probably be more robust
        // You could also just return x.length() - y.length(),
        // which would be more efficient.
        if (x.h < y.h)
        {
            return -1;
        }
        if (x.h > y.h)
        {
            return 1;
        }
        return 0;
    }
    
    
    
//    public static void main(String[] args)
//    {
//        Comparator<Node> comparator = new NodeComparator();
//        PriorityQueue<Node> queue = 
//            new PriorityQueue<Node>(10, comparator);
//        queue.add(new Node(1,2,' ',5));
//        queue.add(new Node(1,2,' ',7));
//        queue.add(new Node(1,2,' ',0));
//        while (queue.size() != 0)
//        {
//            System.out.println(queue.remove().h);
//        }
//    }



}