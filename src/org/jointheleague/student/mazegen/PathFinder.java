package org.jointheleague.student.mazegen;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

class PathFinder {
    private final Node start;
    private final Node finish;
    private final Set<Edge> maze;
    private Stack<Edge> result;
    private boolean done = false;

    PathFinder(Set<Edge> maze, Node start, Node finish) {
        this.maze = maze;
        this.start = start;
        this.finish = finish;
    }

    List<Edge> apply() {
        if (!done) {
            result = new Stack<>();
            findPath(null, finish, start);
        }
        return result;
    }

    private void findPath(Edge comingFrom, Node start, Node finish) {
        if (start == finish) {
            done = true;
        } else {
            for (Iterator<Edge> i = start.getAdjacent().iterator(); !done && i.hasNext();) {
                Edge e = i.next();
                if (e != comingFrom && maze.contains(e)) {
                    Node child = e.getU() == start ? e.getV() : e.getU();
                    findPath(e, child, finish);
                    if (done) result.push(e);
                }
            }
        }
    }
}