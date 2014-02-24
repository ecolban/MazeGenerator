package org.wintrisstech.student.mazegen;

import static org.junit.Assert.*;

import java.util.PriorityQueue;

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
		PriorityQueue<Edge> q = new PriorityQueue<Edge>();
		q.add(new Edge(u, v));
		q.add(new Edge(v, w));
		Edge e1 = q.remove();
		Edge e2 = q.remove();
		assertTrue(e1.getCost() <= e2.getCost());
	}

}
