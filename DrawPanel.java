/*
 Ross Cradock.
 Program that is used by DrawFrane class create a panel to draw shapes and lines upon.
 This class utilizes an inner MouseHandler to track and control the mouse inputs
*/
import java.awt.Color; // import  colors from the abstract window toolkit
import java.awt.Graphics; // import graphics from the abstract window toolkit
import javax.swing.JPanel; // import the drawing panel from the swing framework
import javax.swing.JLabel; // import the label from the swing framework
import java.awt.event.MouseEvent; // import the event object from awt
import java.awt.event.MouseAdapter; // import the mouse adapter to take care of the unused methods in implemented classes
import java.awt.event.MouseMotionListener; // import motion listener to track the mouse position whilst it moves
import java.awt.event.MouseListener; // import the mouse button listener to track the button presses


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
		this.statusLabel = status; // assign the status label with the JLabel opbject passed to the constructor
		shapes = new MyShape[100]; // initialise array of shapes to length of 100 elements
		shapeCount = 0; // assign shape count to zero, the number of shapes in the shapes array
		shapeType = 0; // assign the default shape type
		currentShape = null; // assign null to the currentShape object
		filledShape = false; // assign the filledShape boolean to false as default
		setCurrentColor(Color.BLACK); // set the color selected to black as default
		setBackground(Color.WHITE); // set the background color white so it can be drawn upon
		MouseHandler handler = new MouseHandler(); // initialise the mouse handler inner class
		addMouseListener(handler); // use the handler for the mouse listener
		addMouseMotionListener(handler); // use the handler for the mouse motion listener
    } // end DrawPanel constructor
	
	// for each shape individually call the draw method
    public void paintComponent(Graphics g) {
		super.paintComponent(g); // call the superclass constructor and supply the graphics component
		// draw the shapes from 0 to the length of shapeCount
		for(int i = 0; i < shapeCount; i++){
			// check to see if shape array contains a shape
			if(shapes[i] != null){
				shapes[i].draw(g); // polymorphically call the draw method
			}
		} // end for loop
	} // end method paintComponent
	
	// method to set the shapeType
	public void setShapeType(int type) {
		this.shapeType = type;
	}
	
	// method to set the current color
	public void setCurrentColor(Color color) {
		this.currentColor = color;
	}
	
	// method to set the flag for shape fill
	public void setShapeFill(boolean fill) {
		this.filledShape = fill;
	}
	
	// method to remove the latest most shape from the container
	public void clearLastShape() {
		// check to make sure the shape count will never go below zero
		if(shapeCount >= 1){
			shapeCount--; // decrement the number of shapes to be draw
			repaint(); // call the paintComponent method
		}
	}
	
	// method to remove all the shapes from the container
	public void clearDrawing() {
		shapeCount = 0; // make the number of shapes zero
		repaint(); // call the paintComponent method
	}
	
	// create inner class to handle all mouse events 
	private class MouseHandler extends MouseAdapter implements MouseMotionListener, MouseListener {
		
		// method to handle a pressed mouse event
		public void mousePressed(MouseEvent event) {
			shapeCount++; // increment the number of shapes to be drawn before the shape is created
			switch (shapeType){ // switch statement dependent on the type of shape currently selected
				case 0: // case one corresponds to a line shape
				currentShape = new MyLine(event.getX(), event.getY(), event.getX(), event.getY(), currentColor); // create the current shape
				break;
				case 1: // case two corresponds to a rectangle shape
				currentShape = new MyRect(event.getX(), event.getY(), event.getX(), event.getY(), currentColor, filledShape); // create the current shape
				break;
				case 2: // case three corresponds to a oval shape
				currentShape = new MyOval(event.getX(), event.getY(), event.getX(), event.getY(), currentColor, filledShape); // create the current shape
				break;
			}
			shapes[shapeCount - 1] = currentShape; // assign the currentShape to the shapeCount array index number minus one so that 
			// the mouse dragged method will be able to repeatedly assign to shapeCount minus one and have the shape drawn to shapeCount indstead
		} // end mouse pressed method
		
		// method to handle a released mouse event
		public void mouseReleased(MouseEvent event) {
			currentShape.setX2(event.getX()); // set the bottom right x-coordinate of the shape
			currentShape.setY2(event.getY()); // set the bottom right y-coordinate of the shape
			shapes[shapeCount] = currentShape; // add the current shape to the shapes array at the position of the shapeCount int and increment it
			currentShape = null; // clear the current shape variable so it can be reassigned
			repaint(); // call the repaint method to redraw to the JPanel
		} // end mouse released method		
		
		// method to handle the event of the mouse being dragged across the JFrame
		public void mouseDragged(MouseEvent event) {
			currentShape.setX2(event.getX()); // repeatedly set the bottom right x-coordinate of the shape
			currentShape.setY2(event.getY()); // repeatedly set the bottom right x-coordinate of the shape
			shapes[shapeCount - 1] = currentShape; // set the new shape to shapes array 
			repaint(); // call the repaint method to redraw to the JPanel
			statusLabel.setText(String.format("(%d, %d) ", event.getX(), event.getY())); // update the label with the mouse coordinates
		} // end mouse dragged method
		
		// method to handle the event of the mouse moving
		public void mouseMoved(MouseEvent event) {
			statusLabel.setText(String.format("(%d, %d) ", event.getX(), event.getY())); // update the label with the mouse coordinates
		} //end mouseMoved method 		
	} // end inner class MouseHandler
} // end outer class DrawPanel