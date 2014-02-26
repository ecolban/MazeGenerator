package org.wintrisstech.student.mazegen;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class PathFinder {
	private final Node start;
	private final Node finish;
	private Stack<Edge> result;
	private boolean done = false;

	public PathFinder(Node start, Node finish) {
		this.start = start;
		this.finish = finish;
	}

	public List<Edge> apply() {
		if (!done) {
			result = new Stack<Edge>();
			findPath(null, start, finish);
		}
		return result;
	}

	private void findPath(Edge comingFrom, Node start, Node finish) {
		if (start == finish) {
			done = true;
		} else {
			Set<Edge> adj = start.getAdjacent();
			for (Iterator<Edge> i = adj.iterator(); !done && i.hasNext();) {
				Edge e = i.next();
				if (e != comingFrom && e.isInMaze()) {
					Node child = e.getU() == start ? e.getV() : e.getU();
					result.push(e);
					findPath(e, child, finish);
					if (!done) result.pop();
				}
			}
		}
	}
}
