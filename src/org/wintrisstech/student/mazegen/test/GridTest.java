package org.wintrisstech.student.mazegen.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.wintrisstech.student.mazegen.Edge;
import org.wintrisstech.student.mazegen.Grid;
import org.wintrisstech.student.mazegen.Node;

public class GridTest {

    @Test
    public final void testRunPrim() {
	Node[][] nodes = new Node[4][5];
	// Add all the nodes
	for (int row = 0; row < 4; row++) {
	    for (int col = 0; col < 5; col++) {
		nodes[row][col] = new Node(row, col);
	    }
	}

	int[][] horizontalCosts = { //
	{ 31, 28, 6, 8 }, //
		{ 14, 7, 29, 21 },//
		{ 5, 11, 2, 22 }, //
		{ 23, 17, 18, 13 } };

	int[][] verticalCosts = { { 15, 27, 30, 4, 19 }, { 16, 24, 1, 3, 9 },
		{ 10, 12, 20, 26, 25 } };

	for (int row = 0; row < 4; row++) {
	    for (int col = 0; col < 4; col++) {
		Edge edge = new Edge(nodes[row][col], nodes[row][col + 1],
			horizontalCosts[row][col]);
		nodes[row][col].addEdge(edge);
		nodes[row][col + 1].addEdge(edge);
	    }
	}
	for (int row = 0; row < 3; row++) {
	    for (int col = 0; col < 5; col++) {
		Edge edge = new Edge(nodes[row][col], nodes[row + 1][col],
			verticalCosts[row][col]);
		nodes[row][col].addEdge(edge);
		nodes[row + 1][col].addEdge(edge);
	    }
	}
	Grid grid = new Grid(4, 5);
	List<Edge> mst = grid.runPrim(nodes[0][0]);
	int totalCost = 0;
	for (int i = 0; i < mst.size(); i++) {
	    totalCost += mst.get(i).getCost();
	}
	assertEquals(201, totalCost);
    }
}
