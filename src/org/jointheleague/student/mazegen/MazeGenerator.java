package org.jointheleague.student.mazegen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import javax.swing.SwingUtilities;

public class MazeGenerator {

    private final int numRows;
    private final int numCols;
    private final Node[][] allNodes;

    MazeGenerator(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        allNodes = new Node[numRows][numCols];
    }

    public static void main(String[] args) {
        MazeGenerator generator = new MazeGenerator(100, 100);
        generator.initialize();
        List<Edge> maze = generator.runPrim();
        List<Edge> path = generator.findPath(new HashSet<>(maze));
        MazePanel panel = new MazePanel(generator, maze, path);
        generator.display(panel);
    }

    private List<Edge> findPath(Set<Edge> maze) {
        return new PathFinder(maze, allNodes[0][0], allNodes[numRows - 1][numCols - 1]).apply();
    }

    private void display(MazePanel maze) {
        SwingUtilities.invokeLater(maze);
    }

    void initialize() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                allNodes[i][j] = new Node(i, j);
            }
        }
        // Add horizontal edges
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols - 1; j++) {
                Edge edge = new Edge(allNodes[i][j], allNodes[i][j + 1]);
                allNodes[i][j].addAdjacent(edge);
                allNodes[i][j + 1].addAdjacent(edge);
            }
        }
        // Add vertical edges
        for (int i = 0; i < numRows - 1; i++) {
            for (int j = 0; j < numCols; j++) {
                Edge edge = new Edge(allNodes[i][j], allNodes[i + 1][j]);
                allNodes[i][j].addAdjacent(edge);
                allNodes[i + 1][j].addAdjacent(edge);
            }
        }
    }

    List<Edge> runPrim() {
        List<Edge> result = new ArrayList<>();
        /*
         * 1) queue contains all edges between a connected node and a
         * non-connected node. 2) No edge in queue is between two non-connected
         * nodes.
         */
        allNodes[0][0].setConnected(true);
        PriorityQueue<Edge> queue = new PriorityQueue<>(allNodes[0][0].getAdjacent());
        int count = 1;
        while (!queue.isEmpty()) {
            Edge next = queue.remove();
            Node u = next.getU();
            Node v = next.getV();
            assert u.isConnected() || v.isConnected();
            if (!(u.isConnected() && v.isConnected())) {
                result.add(next);
                Node w = u.isConnected() ? v : u;
                w.setConnected(true);
                for (Edge e : w.getAdjacent()) {
                    if (!(e.getU().isConnected() && e.getV().isConnected())) {
                        queue.add(e);
                    }
                }
                count++;
            }
        }
        assert count == numRows * numCols;
        return result;
    }

    int getNumRows() {
        return numRows;
    }

    int getNumCols() {
        return numCols;
    }

    Node getNode(int row, int col) {
        return allNodes[row][col];
    }
}

