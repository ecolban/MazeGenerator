package org.jointheleague.student.mazegen;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class MazeGeneratorTest {

	@Test
	public final void testMazeGenerator() {
		MazeGenerator gen = new MazeGenerator(10, 20);
		assertEquals(10, gen.getNumRows());
		assertEquals(20, gen.getNumCols());
	}

	@Test
	public final void testInitialize() {
		MazeGenerator gen = new MazeGenerator(10, 20);
		gen.initialize();
		try {
			gen.getNode(9, 19);
		} catch (IndexOutOfBoundsException ex) {
			fail();
		}
		try {
			gen.getNode(10, 19);
			fail();
		} catch (IndexOutOfBoundsException ignore) {
		}
	}

	@Test
	public final void testRunPrim() {
		MazeGenerator gen = new MazeGenerator(1000, 100);
		gen.initialize();
		List<Edge> maze = gen.runPrim();
		assertNotNull(maze);
		assertEquals(99999, maze.size());
	}
}
