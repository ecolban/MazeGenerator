package org.wintrisstech.student.mazegen;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.wintrisstech.erikc.mazegen.Edge;

@SuppressWarnings("serial")
public class Grid extends JPanel implements Runnable, ActionListener {

    private final int numCols;
    private final int numRows;
    private final int numNodes;
    private final Node[][] nodes;

    /* Needed for graphics */
    private static final int CELL_WIDTH = 7;
    private static final Stroke FAT_STROKE = new BasicStroke(CELL_WIDTH - 2);
    private Timer ticker = new Timer(50, this);
    private List<Edge> maze;
    private int endEdgeIndex = 0;
    private int startEdgeIndex = 0;
    private boolean first = true;

    /*-------------------*/

    public Grid(int numRows, int numCols) {
	this.numCols = numCols;
	this.numRows = numRows;
	this.numNodes = numRows * numCols;
	// Build the grid as a 2-dimensional array of Nodes with edges from each
	// node to the neighbor nodes

	// Add all the nodes
	// ...
	// Add all the horizontal edges
	// ...
	// Add all the vertical edges
	// ...

    }

    public static void main(String[] args) {
	Grid grid = new Grid(100, 200);
	SwingUtilities.invokeLater(grid);
    }

    @Override
    public void run() {
	JFrame frame = new JFrame("MST");
	frame.add(this);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setPreferredSize(new Dimension(numCols * CELL_WIDTH, numRows
		* CELL_WIDTH));
	frame.setResizable(false);
	frame.pack();
	frame.setVisible(true);
	maze = runPrim(nodes[0][0]);
	endEdgeIndex = 0;
	ticker.start();

    }

    @Override
    public void paintComponent(Graphics g) {
	Graphics2D g2 = (Graphics2D) g;
	if (first) {
	    g2.setColor(Color.BLACK);
	    g2.fillRect(0, 0, getWidth(), getHeight());
	    first = false;
	}
	g2.setColor(Color.WHITE);
	g2.setStroke(FAT_STROKE);
	for (; startEdgeIndex < endEdgeIndex; startEdgeIndex++) {
	    Edge edge = maze.get(startEdgeIndex);
	    g2.draw(new Line2D.Float(//
		    edge.getU().getCol() * CELL_WIDTH + CELL_WIDTH / 2F, //
		    edge.getU().getRow() * CELL_WIDTH + CELL_WIDTH / 2F, //
		    edge.getV().getCol() * CELL_WIDTH + CELL_WIDTH / 2F, //
		    edge.getV().getRow() * CELL_WIDTH + CELL_WIDTH / 2F));
	}

    }

    public List<Edge> runPrim(Node root) {
	List<Edge> result = new ArrayList<Edge>();
	root.setConnected();
	/*
	 * Keep all edges that join a connected node and an unconnected node in
	 * a priority queue
	 */
	PriorityQueue<Edge> frontier = new PriorityQueue<Edge>();
	// Add all the edges of the root node to the priority queue
	// ...

	// Invariants:
	// 1) result is a minimum cost tree connecting i nodes to root (including root)
	// 2) frontier contains all edges between a node that is connected and
	// one that is not.
	for (int i = 1; i < numNodes; i++) {
	    // ...
	}
	return result;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	repaint();
	if (endEdgeIndex < numNodes - 1) {
	    endEdgeIndex += 1;
	    endEdgeIndex = Math.min(endEdgeIndex, numNodes - 1);
	} else {
	    ticker.stop();
	}
    }

}
