package org.wintrisstech.student.mazegen.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.wintrisstech.student.mazegen.Edge;
import org.wintrisstech.student.mazegen.Node;

public class EdgeTest {

    @Test
    public void testEdge() {
	Edge edge = new Edge(new Node(0, 1), new Node(1, 1));
	assertNotNull(edge);
    }

    @Test
    public void testGetU() {
	Node u = new Node(0, 0);
	Node v = new Node(0, 1);
	Edge edge = new Edge(u, v);
	assertEquals(u, edge.getU());
    }

    @Test
    public void testGetV() {
	Node u = new Node(0, 0);
	Node v = new Node(0, 1);
	Edge edge = new Edge(u, v);
	assertEquals(v, edge.getV());
    }

    @Test
    public void testGetCost() {
	Node u = new Node(0, 0);
	Node v = new Node(0, 1);
	Edge edge = new Edge(u, v);
	assertTrue(1 <= edge.getCost() && edge.getCost() <= 50);
    }

    @Test
    public void testCompareTo() {
	Node u = new Node(0, 0);
	Node v = new Node(0, 1);
	Node w = new Node(1, 0);
	Edge edge1 = new Edge(u, v);
	Edge edge2 = new Edge(v, w);
	if (edge1.getCost() < edge2.getCost())
	    assertTrue(edge1.compareTo(edge2) < 0);
	else if (edge1.getCost() > edge2.getCost())
	    assertTrue(edge1.compareTo(edge2) > 0);
	else
	    assertEquals(0, edge1.compareTo(edge2));
    }
}
