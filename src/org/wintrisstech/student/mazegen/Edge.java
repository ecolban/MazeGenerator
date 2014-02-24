package org.wintrisstech.student.mazegen;

import java.util.Random;

public class Edge implements Comparable<Edge> {
	
	private final Node u;
	private final Node v;
	private final int cost;
	private static final Random RNG = new Random();
	
	public Edge(Node u, Node v) {
		this.u = u;
		this.v = v;
		this.cost = RNG.nextInt(50);
	}
	
	public Node getU() {
		return u;
	}
	
	public Node getV() {
		return v;
	}
	
	public int getCost() {
		return cost;
	}

	@Override
	public int compareTo(Edge that) {
		return this.cost - that.cost ;
	}

}
