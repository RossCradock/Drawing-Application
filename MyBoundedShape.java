/*
Ross Cradock.
Class MyBoundedShape for factoring out the methods  
and variables of MyOval and MyRect
*/

import java.awt.Color;
import java.awt.Graphics;

public abstract class MyBoundedShape extends MyShape {
	private boolean shapeFill;
	
	public MyBoundedShape(int x1Coordinate, int y1Coordinate, int x2Coordinate, int y2Coordinate, Color color, boolean fill) {
		super(x1Coordinate, y1Coordinate, x2Coordinate, y2Coordinate, color);
		setFill(fill);
	}
	
	public MyBoundedShape() {
		super(0, 0, 0, 0, Color.BLACK);
		setFill(false);
	}
	
	public void setFill(boolean fill) {
		this.shapeFill = fill;
	}
	
	public boolean getFill() {
		return shapeFill;
	}
	
	public abstract void draw(Graphics g);
}