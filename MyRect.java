/*
Ross Cradock.
Class MyRect to create instance of MyShape with
coordinantes and colours of the rectangles 
and to store a boolean flag for shape fill.
*/

import java.awt.Color; // get colors from the abstract window toolkit
import java.awt.Graphics; // get graphics from the abstract window toolkit

public class MyRect extends MyBoundedShape {
	
	// Full-argument constructor with all input values
	public MyRect(int x1Coordinate, int y1Coordinate, int x2Coordinate, int y2Coordinate, Color color, boolean fill) {
		super(x1Coordinate, y1Coordinate, x2Coordinate, y2Coordinate, color, fill); // call the superclass constructor with supplied arguments
	} // end full-argument constructor
	
	// No-argument constructor with no input values
	public MyRect() {
		super(0, 0, 0, 0, Color.BLACK, false); // set all default coordinate values in the MyShape constructor to 0 and the colour to black
	} // end no-argument constructor
	
	// Method to draw the rectangle
	public void draw(Graphics g) {
		g.setColor(getColor());
		// check whether the shape is filled or not
		if(getFill()){
			g.fillRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight()); // draw a filled-in rectangle using methods from superclass
		} else {
			g.drawRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight()); // draw an  empty rectangle using methods from superclass
		} // end if statement
	} // end draw method
} // end class MyRect