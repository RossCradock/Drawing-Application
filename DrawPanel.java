// Ross Cradock.
// Program that uses class MyShape to draw random shapes and lines.

import java.awt.Color; // get colors from the abstract window toolkit
import java.awt.Graphics; // get graphics from the abstract window toolkit
import javax.swing.JPanel; // get the drawing panel from the swing framework

public class DrawPanel extends JPanel {
	private MyShape shapes[]; // declare array of class objects MyShape
	private int shapeCount; // declare an integer to count the number of shapes in the array
	private int shapeType; // declare the int which corresponds to the type of shape
	private MyShape currentShape; // declare a MyShape object for the user's currently selected shape type
	private Color currentColor; // declare a Color object for the user's currently selected co
	private boolean filledShape; // declare a boolean flag which the user 
	private JLabel statusLabel; // declare the JLabel which will describe the current coordinates
	
	// Constructor that creates a panel with the random shapes
	public DrawPanel(JLabel status) {
		statusLabel = status; // assign the status label with the JLabel opbject passed to the constructor
		shapeCount = 0; // assign shape count to zero, the number of shapes in the shapes array
		shapes = new MyShape[100]; // initiialise array of shapes to length of 100 elements
		shapeType = 1; // assign the default shape type
		currentShape = null;
		currentColor = Color.BLACK;
		setBackground(Color.WHITE);
		MouseHandler handler = new MouseHandler();
		super.addMouseListener(handler);
		super.addMouseMotionListener(handler);
		
		/*
		// create line shapes
		for(int count = 0; count < numberOfLines; count++) {
			// generate random coordinates
			int x1 = random.nextInt(300);
			int y1 = random.nextInt(300);
			int x2 = random.nextInt(300);
			int y2 = random.nextInt(300);
			Color color = new Color(random.nextInt(256), 
				random.nextInt(256), random.nextInt(256)); // generate a random color
			shapes[count] = new MyLine(x1, y1, x2, y2, color); // add the line to the array of shapes to be displayed	
		} // end for loop
		
		// create rectangle shapes starting from number of lines until the number of ovals
		for(int count = numberOfLines; count < shapes.length - numberOfOvals; count++) {
			// generate random coordinates
			int x1 = random.nextInt(300);
			int y1 = random.nextInt(300);
			int x2 = random.nextInt(300);
			int y2 = random.nextInt(300);
			Color color = new Color(random.nextInt(256), 
				random.nextInt(256), random.nextInt(256)); // generate a random color
			boolean fill = random.nextBoolean(); // generate a random boolean
			shapes[count] = new MyRect(x1, y1, x2, y2, color, fill); // add the rectangles to the array of shapes to be displayed
		} // end for loop
		
		// create oval shapes starting from the number of lines and rectangles until the total number of shapes
		for(int count = numberOfLines + numberOfRects; count < shapes.length; count++) {
			// generate random coordinates
			int x1 = random.nextInt(300);
			int y1 = random.nextInt(300);
			int x2 = random.nextInt(300);
			int y2 = random.nextInt(300);
			Color color = new Color(random.nextInt(256), 
				random.nextInt(256), random.nextInt(256)); // generate a random color
			boolean fill = random.nextBoolean(); // generate a random boolean
			shapes[count] = new MyOval(x1, y1, x2, y2, color, fill); // add the oval to the array of shapes to be displayed
			*/
		} // end for loop
    } // end DrawPanel constructor
	
	// method to set the shapeType
	public void setShapeType(int type) {
		shapeType = type;
	}
	
	// method to set the current color
	public void setCurrentColor(Color color) {
		curentColor = color;
	}
	
	// method to set the flag for shape fill
	public void setShapeFill(boolean fill) {
		shapeFill = fill;
	}
	
	// method to remove the latest most shape from the container
	public void clearLastShape(shapeCount) {
		// check to make sure the currentShape will never go below zero
		if(currentShape >= 1){
			currentShape--;
			repaint();
		}
	}
	
	// method to remove all the shapes from the container
	public void clearDrawing() {
		currentShape = 0;
		repaint();
	}
	
	// method to return the number of shapes used
	public String status() {
		return String.format("%s: %d, %s: %d, %s: %d",
			"Lines", numberOfLines,
			"Ovals", numberOfOvals,
			"Rectangles", numberOfRects); // return the number of each shape type in shapes array
	} // end status method
	
    // for each shape individually call the draw method
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// draw the shapes
		for(int i = 0; i < shapeCount; i++){
			// check to see if shape array contains a shape
			if(currentShape != null){
				shapes[i].draw(g); // polymorphically call the draw method
			}
		} // end for loop
	} // end method paintComponent
	
	// create inner class to handle all mouse events 
	private class MouseHandler extends MouseAdapter implements MouseMotionListener, MouseListener {
		
		// required method to handle a mouse click event
		public void mouseClicked(MouseEvent event) {
			// do nothing with mouse click method
		} // end mouse clicked method
		
		// required method to handle a pressed mouse event
		public void mousePressed(MouseEvent event) {
			switch (shapeType){ // switch statement dependent on the type of shape currently selected
				case 1: // case one corresponds to a line shape
				currentShape = MyLine; // assign the current shape
				break;
				case 2: // case two corresponds to a rectangle shape
				currentShape = MyRect; // assign the current shape
				break;
				case 3: // case three corresponds to a oval shape
				currentShape = MyOval; // assign the current shape
				break;
			}
			currentShape.setX1(event.getX()); // set the top left x-coordinate of the shape
			currentShape.setY1(event.getY()); // set the top left y-coordinate of the shape
		} // end mouse pressed method
		
		// required method to handle a released mouse event
		public void mouseReleased(MouseEvent event) {
			currentShape.setX2(event.getX()); // set the bottom right x-coordinate of the shape
			currentShape.setY2(event.getY()); // set the bottom right y-coordinate of the shape
			shapes[shapeCount++] = currentShape; // add the current shape to the shapes array at the position of the shapeCount int and increment it
			currentShape = null; // clear the current shape variable so it can be reassigned
			repaint(); // call the repaint method to redraw to the JPanel
		} // end mouse released method
		
		// required method to handle the event of the mouse returning to the JFrame
		public void mouseEntered(MouseEvent event) {
			// do nothing with mouse enter method
		} //end mouse entered method
		
		// required method to handle the event of the mouse exiting the JFrame
		public void mouseExited(MouseEvent event) {
			// do nothing with the mouse exit method
		} // end mouse exited method
		
		// required method to handle the event of the mouse being dragged across the JFrame
		public void mouseDragged(MouseEvent event) {
			currentShape.setX2(event.getX()); // repeatedly set the bottom right x-coordinate of the shape
			currentShape.setY2(event.getY()); // repeatedly set the bottom right x-coordinate of the shape
			shapes[shapeCount] = currentShape; // repeatedly add the current shape to the shapes array at the position of shape count
			repaint(); // call the repaint method to redraw to the JPanel
		} // end mouse dragged method
		
		// required method to handle the event of the mouse moving
		public void mouseMoved(MouseEvent event) {
			statusLabel.setText(String.format("(%d, %d) ", event.getX(), event.getY())); // update the label with the mouse coordinates
		} //end mouseMoved method 
	} // end inner class MouseHandler
} // end outer class DrawPanel