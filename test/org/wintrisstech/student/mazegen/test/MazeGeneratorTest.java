package org.wintrisstech.student.mazegen.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.wintrisstech.student.mazegen.Edge;
import org.wintrisstech.student.mazegen.MazeGenerator;

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
	} catch (IndexOutOfBoundsException ex) {
	}
	try {
	    gen.getVtEge(8, 0);
	} catch (IndexOutOfBoundsException ex) {
	    fail();
	}
	try {
	    gen.getVtEge(9, 0);
	    fail();
	} catch (IndexOutOfBoundsException ex) {
	}
	try {
	    gen.getHzEge(0, 18);
	} catch (IndexOutOfBoundsException ex) {
	    fail();
	}
	try {
	    gen.getHzEge(0, 19);
	    fail();
	} catch (IndexOutOfBoundsException ex) {
	}
    }
    
    @Test
    public final void testAdjacentEdges() {
	MazeGenerator gen = new MazeGenerator(10, 20);
	assertEquals(10, gen.getNumRows());
	assertEquals(20, gen.getNumCols());
	gen.initialize();
	List<Edge> adj = gen.adjacentEdges(gen.getNode(9,19));
	assertEquals(2, adj.size());
	List<Edge> adj2 = gen.adjacentEdges(gen.getNode(1, 0));
	assertEquals(3, adj2.size());
	List<Edge> adj3 = gen.adjacentEdges(gen.getNode(1, 1));
	assertEquals(4, adj3.size());
    }

    
    @Test
    public final void testRunPrim() {
	MazeGenerator gen = new MazeGenerator(10, 10);
	gen.initialize();
	List<Edge> maze = gen.runPrim();
	assertNotNull(maze);
	assertEquals(99, maze.size());
    }
}
