package org.jointheleague.student.mazegen;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Node {

	private final int row;
	private final int col;
	private boolean connected = false;

	private Set<Edge> adjacent = new HashSet<>();

	Node(int row, int col) {
		this.row = row;
		this.col = col;
	}

	int getRow() {
		return this.row;
	}

	int getCol() {
		return col;
	}
	void setConnected(boolean b) {
		connected = b;
	}

	boolean isConnected() {
		return connected;
	}
	
	void addAdjacent(Edge edge) {
		adjacent.add(edge);
	}
	
	Set<Edge> getAdjacent() {
		return adjacent;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Node node = (Node) o;
		return row == node.row && col == node.col;
	}

	@Override
	public int hashCode() {
		return Objects.hash(row, col);
	}

	@Override
	public String toString() {
		return String.format("Node(row=%d, col=%d, connected=%s)", row, col, connected);
	}


}
