// Ross Cradock.
// Class MyLine to construct class fields for the
// coordinantes and colours of the line.

import java.awt.Color; // get colors from the abstract window toolkit
import java.awt.Graphics; // get graphics from the abstract window toolkit

public class MyLine extends MyShape {
    // Full-argument constructor with all input values
    public MyLine(int x1Coordinate, int y1Coordinate, int x2Coordinate, int y2Coordinate, Color color) {
		super(x1Coordinate, y1Coordinate, x2Coordinate, y2Coordinate, color); // call the superclass constructor with supplied arguments
	} // end MyLine full-argument constructor
   
	// No-argument constructor with no input values
	public MyLine() {
		this(0, 0, 0, 0, Color.BLACK); // set all default coordinate values to 0 and the colour to black
	} // end MyLine no-argument constructor
	
	// Draw the line
	public void draw(Graphics g) {
	g.setColor(getColor()); // set the line's colour
		g.drawLine(getX1(), getY1(), getX2(), getY2()); // set the coordinates using superclass' methods
	} // end method draw
} // end class MyLine

