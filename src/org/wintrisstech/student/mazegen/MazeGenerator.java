package org.wintrisstech.student.mazegen;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import javax.swing.SwingUtilities;

public class MazeGenerator {

    private final int numRows;
    private final int numCols;
    private final Node[][] allNodes;
    private final Edge[][] allHzEdges;
    private final Edge[][] allVtEdges;

    public MazeGenerator(int numRows, int numCols) {
	this.numRows = numRows;
	this.numCols = numCols;
	allNodes = new Node[numRows][numCols];
	allHzEdges = new Edge[numRows][numCols - 1];
	allVtEdges = new Edge[numRows - 1][numCols];
    }

    public static void main(String[] args) {

	MazeGenerator generator = new MazeGenerator(25, 125);
	generator.initialize();
	List<Edge> maze = generator.runPrim();
	generator.display(maze);
    }

    private void display(List<Edge> maze) {
	SwingUtilities.invokeLater(new MazePanel(maze, numRows, numCols));
    }

    public void initialize() {
	for (int i = 0; i < numRows; i++) {
	    for (int j = 0; j < numCols; j++) {
		allNodes[i][j] = new Node(i, j);
	    }
	}
	for (int i = 0; i < numRows - 1; i++) {
	    for (int j = 0; j < numCols; j++) {
		allVtEdges[i][j] = new Edge(allNodes[i][j], allNodes[i + 1][j]);
	    }
	}
	for (int i = 0; i < numRows; i++) {
	    for (int j = 0; j < numCols - 1; j++) {
		allHzEdges[i][j] = new Edge(allNodes[i][j], allNodes[i][j + 1]);
	    }
	}
    }

    public List<Edge> runPrim() {
	List<Edge> result = new ArrayList<Edge>();
	PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
	Node root = allNodes[0][0];
	root.setConnected(true);
	queue.addAll(adjacentEdges(root));
	int count = 1;
	while (!queue.isEmpty()) {
	    Edge next = queue.poll();
	    Node u = next.getU();
	    Node v = next.getV();
	    assert u.isConnected() || v.isConnected();
	    if (!v.isConnected()) {
		result.add(next);
		v.setConnected(true);
		queue.addAll(adjacentEdges(v));
		count++;
	    } else if (!u.isConnected()) {
		result.add(next);
		u.setConnected(true);
		queue.addAll(adjacentEdges(u));
		count++;
	    } else {
		// do nothing
	    }
	}
	assert count == numRows * numCols;
	return result;
    }

    public List<Edge> adjacentEdges(Node node) {
	List<Edge> result = new ArrayList<Edge>();
	int row = node.getRow();
	int col = node.getCol();
	assert 0 <= row && row < numRows;
	assert 0 <= col && col < numCols;
	if (0 < row)
	    result.add(allVtEdges[row - 1][col]);
	if (row < numRows - 1)
	    result.add(allVtEdges[row][col]);
	if (0 < col)
	    result.add(allHzEdges[row][col - 1]);
	if (col < numCols - 1)
	    result.add(allHzEdges[row][col]);
	return result;
    }

    public int getNumRows() {
	return numRows;
    }

    public int getNumCols() {
	return numCols;
    }

    public Node getNode(int row, int col) {
	return allNodes[row][col];
    }

    public Edge getVtEge(int row, int col) {
	return allVtEdges[row][col];
    }
    
    public Edge getHzEge(int row, int col) {
	return allHzEdges[row][col];
    }

}
