package org.jointheleague.student.mazegen;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Edge implements Comparable<Edge> {
	
	private final Node u;
	private final Node v;
	private final int cost;

	Edge(Node u, Node v) {
		this.u = u;
		this.v = v;
		this.cost = ThreadLocalRandom.current().nextInt(50);
		u.addAdjacent(this);
		v.addAdjacent(this);
	}
	
	Node getU() {
		return u;
	}
	
	Node getV() {
		return v;
	}
	
	int getCost() {
		return cost;
	}

	@Override
	public int compareTo(Edge that) {
		return this.cost - that.cost ;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Edge edge = (Edge) o;
		return u.equals(edge.u) && v.equals(edge.v);
	}

	@Override
	public int hashCode() {
		return Objects.hash(u, v);
	}

	@Override
	public String toString() {
		return String.format("Edge(u=%s, v=%s)", u, v);
	}

}
