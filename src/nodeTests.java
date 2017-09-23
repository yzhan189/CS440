import static org.junit.Assert.*;


import org.junit.Test;

import maze.Node;

public class nodeTests {

	@Test
	public void test() {
		Node n = new Node(1,2,'d');
		assert(n.getRow()==1);
		assert(n.getCol()==2);
		assert(n.getType()=='d');
		assert(n.getNorth()==null);
		assert(n.getSouth()==null);
		assert(n.getEast()==null);
		assert(n.getWest()==null);
		
	}

}
