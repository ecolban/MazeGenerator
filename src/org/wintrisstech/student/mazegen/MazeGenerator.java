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
		MazeGenerator generator = new MazeGenerator(25, 25);
		generator.initialize();
		List<Edge> maze = generator.runPrim();
		List<Edge> path = generator.findPath();
		MazePanel panel = new MazePanel(generator, maze, path);
		generator.display(panel);
	}

	private List<Edge> findPath() {
		return new PathFinder(allNodes[0][0],
				allNodes[numRows - 1][numCols - 1]).apply();
	}

	private void display(MazePanel maze) {
		SwingUtilities.invokeLater(maze);
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
		/*
		 * 1) queue contains all edges between a connected node and a
		 * non-connected node. 2) No edge in queue is between two non-connected
		 * nodes.
		 */
		PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
		allNodes[0][0].setConnected(true);
		queue.addAll(allNodes[0][0].getAdjacent());
		int count = 1;
		while (!queue.isEmpty()) {
			Edge next = queue.remove();
			Node u = next.getU();
			Node v = next.getV();
			assert u.isConnected() || v.isConnected();
			if (!(u.isConnected() && v.isConnected())) {
				result.add(next);
				next.setInMaze(true);
				Node w = u.isConnected() ? v : u;
				w.setConnected(true);
				queue.addAll(w.getAdjacent());
				count++;
			}
		}
		assert count == numRows * numCols;
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

	public Edge getVtEdge(int row, int col) {
		return allVtEdges[row][col];
	}

	public Edge getHzEdge(int row, int col) {
		return allHzEdges[row][col];
	}

}
