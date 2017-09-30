//import static org.junit.Assert.*;
//
//import java.io.File;
//import org.apache.commons.io.FileUtils;
//import java.io.IOException;
//
//import org.junit.Test;
//
//import maze.Maze;
//
//
//public class mazePrintTests {
//
//	boolean print = false;
//
//	@Test
//	public void printTinySearch() throws IOException {
//		Maze m = new Maze("tinySearch");
//		if(print) m.printMaze();
//		assert(m.getHeight()==9);
//		assert(m.getWidth()==10);
//	}
//
//	@Test
//	public void printMediumMaze() throws IOException {
//		Maze m = new Maze("mediumMaze");
//		if(print) m.printMaze();
//		assert(m.getHeight()==23);
//		assert(m.getWidth()==61);
//	}
//
//	@Test
//	public void printOpenMaze() throws IOException {
//		Maze m = new Maze("openMaze");
//		if(print) m.printMaze();
////		System.out.println("\""+m.maze[19][0].type+"\"");
////		System.out.println("\""+m.maze[19][37].type+"\"");
////		System.out.println("\""+m.maze[19][38].type+"\"");
//		assert(m.getHeight()==20);
//		assert(m.getWidth()==37);
//	}
//
//
//	@Test
//	public void printBigDots() throws IOException {
//		Maze m = new Maze("bigDots");
//		if(print) m.printMaze();
//		assert(m.getHeight()==17);
//		assert(m.getWidth()==28);
//	}
//
//
//}
