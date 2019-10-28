package org.jointheleague.student.mazegen;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;

@SuppressWarnings("serial")
public class MazePanel extends JPanel implements Runnable {

    private final Iterator<Edge> edgeIterator;
    private Iterator<Edge> pathIterator;
    private final int cellWidth = 6;
    private final Timer ticker = new Timer(3, this::drawNextEdge);
    private final BufferedImage mazeImage;
    private final Graphics2D mazeGraphics;

    MazePanel(MazeGenerator generator, List<Edge> maze, List<Edge> path) {
        int numRows = generator.getNumRows();
        int numCols = generator.getNumCols();
        edgeIterator = maze.iterator();
        pathIterator = path.iterator();
        mazeImage = new BufferedImage(cellWidth * numCols, cellWidth * numRows, BufferedImage.TYPE_INT_ARGB);
        mazeGraphics = mazeImage.createGraphics();
        mazeGraphics.setColor(Color.BLACK);
        mazeGraphics.fillRect(0, 0, mazeImage.getWidth(), mazeImage.getHeight());
        mazeGraphics.setColor(Color.WHITE);
        Stroke edgeStroke = new BasicStroke(cellWidth - 1F);
        mazeGraphics.setStroke(edgeStroke);
        ticker.start();
    }

    @Override
    public void run() {
        setPreferredSize(new Dimension(mazeImage.getWidth(), mazeImage.getHeight()));
        JFrame frame = new JFrame("Maze");
        frame.add(this);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(mazeImage, null, 0, 0);
    }

    private void drawNextEdge(ActionEvent e) {
        int cellCenterOffset = cellWidth / 2;
        if (edgeIterator.hasNext()) {
            Edge edge = edgeIterator.next();
            mazeGraphics.drawLine(edge.getU().getCol() * cellWidth + cellCenterOffset,
                    edge.getU().getRow() * cellWidth + cellCenterOffset, //
                    edge.getV().getCol() * cellWidth + cellCenterOffset, //
                    edge.getV().getRow() * cellWidth + cellCenterOffset);
            repaint();
        } else {
            mazeGraphics.setColor(Color.GREEN);
            if (pathIterator.hasNext()) {
                Edge edge = pathIterator.next();
                mazeGraphics.drawLine(edge.getU().getCol() * cellWidth + cellCenterOffset,
                        edge.getU().getRow() * cellWidth + cellCenterOffset, //
                        edge.getV().getCol() * cellWidth + cellCenterOffset, //
                        edge.getV().getRow() * cellWidth + cellCenterOffset);
                repaint();
            } else {
                ticker.stop();
            }
        }
    }
}
