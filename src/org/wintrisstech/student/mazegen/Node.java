package org.wintrisstech.student.mazegen;


public class Node {
    
    private final int row;
    private final int col;
    private boolean connected = false;

    public Node(int row, int col) {
	this.row = row;
	this.col = col;
    }

    public int getRow() {
	
	return this.row;
    }

    public int getCol() {
	// TODO Auto-generated method stub
	return col;
    }


    public void setConnected(boolean b) {
	connected = b;
	
    }

    public boolean isConnected() {
	// TODO Auto-generated method stub
	return connected;
    }

}
