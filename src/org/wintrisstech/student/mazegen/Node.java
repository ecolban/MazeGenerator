package org.wintrisstech.student.mazegen;

import java.util.HashSet;
import java.util.Set;

public class Node {

	private final int row;
	private final int col;
	private boolean connected = false;
	private Set<Edge> adjacent = new HashSet<Edge>();

	public Node(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {

		return this.row;
	}

	public int getCol() {
		return col;
	}

	public void setConnected(boolean b) {
		connected = b;

	}

	public boolean isConnected() {
		return connected;
	}
	
	public void addAdjacent(Edge edge) {
		adjacent.add(edge);
	}
	
	public Set<Edge> getAdjacent() {
		return adjacent;
	}

}
