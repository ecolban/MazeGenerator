package org.wintrisstech.student.mazegen;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MazePanel extends JPanel implements Runnable {

	private final int numRows;
	private final int numCols;
	private final List<Edge> maze;
	private final int cellWidth = 6;
	private final int cellCenterOffset = cellWidth / 2;
	private final Stroke edgeStroke = new BasicStroke(cellWidth - 1F);
	private final Stroke pathStroke = new BasicStroke(1F);
	private final List<Edge> path;

	public MazePanel(MazeGenerator generator, List<Edge> maze, List<Edge> path) {
		this.numRows = generator.getNumRows();
		this.numCols = generator.getNumCols();
		this.maze = maze;
		this.path = path;
	}

	@Override
	public void run() {

		setPreferredSize(new Dimension(cellWidth * numCols, cellWidth * numRows));
		JFrame frame = new JFrame("Maze");
		frame.add(this);
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// fill background
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getWidth(), getHeight());
		// draw maze
		g2.setColor(Color.WHITE);
		g2.setStroke(edgeStroke);
		drawEdges(g2, maze);
		// draw path
		g2.setColor(Color.RED.darker());
		g2.setStroke(pathStroke);
		drawEdges(g2, path);
	}

	private void drawEdges(Graphics2D g2, List<Edge> edges) {
		for (Edge edge : edges) {
			g2.drawLine(edge.getU().getCol() * cellWidth + cellCenterOffset,
					edge.getU().getRow() * cellWidth + cellCenterOffset, //
					edge.getV().getCol() * cellWidth + cellCenterOffset, //
					edge.getV().getRow() * cellWidth + cellCenterOffset);
		}
	}
}
