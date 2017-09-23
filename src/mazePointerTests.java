import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import maze.Maze;
import maze.Node;

public class mazePointerTests {

	@Test
	public void test() throws IOException {		
		Maze m = new Maze("tinySearch");
		Node root = m.getRoot();
		assert(root == (m.getMazeSquare(4,4)));
		assert(root.getNorth()==m.getMazeSquare(3,4));
		assert(root.getSouth()==m.getMazeSquare(5,4));
		assert(root.getEast()==m.getMazeSquare(4,5));
		assert(root.getWest()==m.getMazeSquare(4,3));
		
		Node upright = m.getMazeSquare(0,0);
		assert(upright.getNorth()==null);
		assert(upright.getWest()==null);
		
		Node downleft = m.getMazeSquare(m.getHeight()-1,m.getWidth()-1);
		assert(downleft.getNorth()!=null);
		assert(downleft.getWest()!=null);
		assert(downleft.getSouth()==null);
		assert(downleft.getEast()==null);	
	}

}
