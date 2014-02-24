package org.wintrisstech.student.mazegen;

import static org.junit.Assert.*;

import org.junit.Test;
import org.wintrisstech.student.mazegen.Node;

public class NodeTest {

	Node node_0_0 = new Node(0, 0);
	Node node_0_1 = new Node(0, 1);
	Node node_1_0 = new Node(1, 0);
	Node node_1_1 = new Node(1,1);

	@Test
	public void testNode() {
//		node_0_0 = new Node(0, 0);
//		node_0_1 = new Node(0, 1);
//		node_1_0 = new Node(1, 0);
//		node_1_1 = new Node(1, 1);
		assertNotNull(node_0_0);
		assertNotNull(node_0_1);
		assertNotNull(node_1_0);
		assertNotNull(node_1_1);
	}

	@Test
	public void testGetRow() {
		assertEquals(0, node_0_0.getRow());
		assertEquals(0, node_0_1.getRow());
		assertEquals(1, node_1_0.getRow());
		assertEquals(1, node_1_1.getRow());
	}

	@Test
	public void testGetCol() {
		assertEquals(0, node_0_0.getCol());
		assertEquals(1, node_0_1.getCol());
		assertEquals(0, node_1_0.getCol());
		assertEquals(1, node_1_1.getCol());
	}

	@Test
	public void testAddEdge() {

	}


	@Test
	public void testSetConnected() {
	}

	@Test
	public void testIsConnected() {
		node_0_0.setConnected(true);
		assertTrue(node_0_0.isConnected());
		assertFalse(node_0_1.isConnected());
	}

}
