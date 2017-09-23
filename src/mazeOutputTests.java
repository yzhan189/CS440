import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import maze.Maze;

public class mazeOutputTests {

	@Test
	public void outputTinySearch() throws IOException {
		String mazeName = "tinySearch";
		Maze m = new Maze( mazeName);
		m.outputMaze(mazeName);
		
		File file1 = new File(mazeName+".txt");
		File file2 = new File(mazeName+".out");
		assert(FileUtils.contentEquals(file1, file2));		
		
	}
	
	@Test
	public void outputMediumMaze() throws IOException {
		String mazeName = "mediumMaze";
		Maze m = new Maze( mazeName);
		m.outputMaze(mazeName);
		
		File file1 = new File(mazeName+".txt");
		File file2 = new File(mazeName+".out");
		assert(FileUtils.contentEquals(file1, file2));		
		
	}
	
	@Test
	public void outputMediumSearch() throws IOException {
		String mazeName = "mediumSearch";
		Maze m = new Maze( mazeName);
		m.outputMaze(mazeName);
		
		File file1 = new File(mazeName+".txt");
		File file2 = new File(mazeName+".out");
		assert(FileUtils.contentEquals(file1, file2));		
		
	}
	
	@Test
	public void outputBigMaze() throws IOException {
		String mazeName = "bigMaze";
		Maze m = new Maze( mazeName);
		m.outputMaze(mazeName);
		
		File file1 = new File(mazeName+".txt");
		File file2 = new File(mazeName+".out");
		assert(FileUtils.contentEquals(file1, file2));			
	}
	
	@Test
	public void outputOpenMaze() throws IOException {
		String mazeName = "openMaze";
		Maze m = new Maze( mazeName);
		m.outputMaze(mazeName);
		
		File file1 = new File(mazeName+".txt");
		File file2 = new File(mazeName+".out");
		assert(FileUtils.contentEquals(file1, file2));			
	}

	@Test
	public void outputSmallSearch() throws IOException {
		String mazeName = "smallSearch";
		Maze m = new Maze( mazeName);
		m.outputMaze(mazeName);
		
		File file1 = new File(mazeName+".txt");
		File file2 = new File(mazeName+".out");
		assert(FileUtils.contentEquals(file1, file2));			
	}
	
	@Test
	public void outputBigDots() throws IOException {
		String mazeName = "bigDots";
		Maze m = new Maze( mazeName);
		m.outputMaze(mazeName);
		
		File file1 = new File(mazeName+".txt");
		File file2 = new File(mazeName+".out");
		assert(FileUtils.contentEquals(file1, file2));			
	}

}
