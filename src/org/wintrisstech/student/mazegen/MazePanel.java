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
	private final Stroke edgeStroke = new BasicStroke(cellWidth - 2F);

	public MazePanel(List<Edge> maze, int numRows, int numCols) {
		this.numRows = numRows;
		this.numCols = numCols;
		this.maze = maze;
	}

	@Override
	public void run() {

		setPreferredSize(new Dimension(cellWidth * numCols, cellWidth * numRows));
		JFrame frame = new JFrame("Maze");
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setColor(Color.WHITE);
		g2.setStroke(edgeStroke);
		for (Edge edge : maze) {
			g2.drawLine(edge.getU().getCol() * cellWidth + cellCenterOffset,
					edge.getU().getRow() * cellWidth + cellCenterOffset, //
					edge.getV().getCol() * cellWidth + cellCenterOffset, //
					edge.getV().getRow() * cellWidth + cellCenterOffset);
		}
	}

}
