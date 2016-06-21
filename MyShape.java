// Ross Cradock.
// 

import java.awt.Color; // get colors from the abstract window toolkit
import java.awt.Graphics; // get graphics from the abstract window toolkit

public abstract class MyShape {
	private int x1; // x-coordinate of first endpoint for shape
	private int y1; // y-coordinate of first endpoint for shape
	private int x2; // x-coordinate of second endpoint for shape
	private int y2; // y-coordinate of second endpoint for shape
	private Color myColor; // colour of the shape
	
	// Full-argument constructor with all input values
	public MyShape(int x1Coordinate, int y1Coordinate, int x2Coordinate, int y2Coordinate, Color color) {
		setX1(x1Coordinate); // set x-coordinate of first endpoint for shape
		setY1(y1Coordinate); // set y-coordinate of first endpoint for shape
		setX2(x2Coordinate); // set x-coordinate of second endpoint for shape
		setY2(y2Coordinate); // set y-coordinate of second endpoint for shape
		setColor(color); // set colour of the shape
	} // end full-argument constructor
	
	// No-argument constructor with no input values
	public MyShape() {
		this(0, 0, 0, 0, Color.BLACK); // set all default coordinate values to 0, the colour to black and the fill to false
	} // end no-argument constructor
	
	// set method with positive and non-zero check for x1
	public void setX1(int x1Coordinate) {
		this.x1 = (x1Coordinate >= 0) ? x1Coordinate : 0; // verification that the input value for x1 is greater than or equal to zero
	} // end set method
	
	// Method to get x1
	public int getX1(){
		return x1;
	} //end method
	
	// set method with positive and non-zero check for y1
	public void setY1(int y1Coordinate) {
		this.y1 = (y1Coordinate >= 0) ? y1Coordinate : 0; // verification that the input value for y1 is greater than or equal to zero
	} // end set method
	
	// Method to get y1
	public int getY1(){
		return y1;
	} //end method
	
	// set method with positive and non-zero check for x2
	public void setX2(int x2Coordinate) {
		this.x2 = (x2Coordinate >= 0) ? x2Coordinate : 0; // verification that the input value for x2 is greater than or equal to zero
	} // end set method
	
	// Method to get x2
	public int getX2(){
		return x2;
	} //end method
	
	// set method with positive and non-zero check for y2
	public void setY2(int y2Coordinate) {
		this.y2 = (y2Coordinate >= 0) ? y2Coordinate : 0; // verification that the input value for y2 is greater than or equal to zero
	} // end set method
	
	// Method to get y2
	public int getY2(){
		return y2;
	} //end method
	
	// set method for colour argument
	public void setColor(Color color) {
		this.myColor = color; // set colour of the shape
	} // end set method
	
	// Method to get colour of shape
	public Color getColor(){
		return myColor;
	} //end method
	
	// Method to get top left x-coordinate
	public int getUpperLeftX() {
		int upperLeftX = (x1 < x2) ? x1 : x2; // determine which x coordinate is furtherest left
		return upperLeftX;
	} // end method
	
	// Method to get top left y-coordinate
	public int getUpperLeftY() {
		int upperLeftY = (y1 < y2) ? y1 : y2; // determine which y coordinate is the highest
		return upperLeftY;
	} // end method
	
	// Method to get top left x-coordinate
	public int getWidth() {
		int width = Math.abs(x1 - x2); // the absolute difference between the x1 and x2 coordinates gives the width
		return width;
	} // end method
	
	// Method to get top left x-coordinate
	public int getHeight() {
		int height = Math.abs(y1 - y2); // the absolute difference between the y1 and y2 coordinates gives the height
		return height;
	} // end method
	
	public abstract void draw(Graphics g);
}