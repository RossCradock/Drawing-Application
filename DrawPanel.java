// Ross Cradock.
// Program that uses class MyShape to draw random shapes and lines.

import java.awt.Color; // get colors from the abstract window toolkit
import java.awt.Graphics; // get graphics from the abstract window toolkit
import javax.swing.JPanel; // get the drawing panel from the swing framework
import java.util.Random; // get random number generator from java utilities class
import java.util.Scanner; // get the scanner to allow user inputs

public class DrawPanel extends JPanel {
	private Random random = new Random(); // Random object to create random numbers and booleans
	private MyShape shapes[]; // declare array of class objects MyShape
	private int shapeCount; // declare an integer to count the number of shapes int he array
	private int shapeType; // declare the int which corresponds to the type of shape
	private MyShape currentShape; // declare a MyShape object for the user's currently selected shape type
	private Color currentColor; // declare a Color object for the user's currently selected co
	private boolean filledShape; // declare a boolean flag which the user 
	private JLabel statusLabel; // declare the JLabel which will describe the current coordinates
	
	// Constructor that creates a panel with the random shapes
	public DrawPanel() {
		setBackground(Color.WHITE);
		Scanner input = new Scanner(System.in); // initialise the the scanner object for reading user inputs
		System.out.println("Enter the number of lines to be drawn :"); // ask the user for number of lines
		numberOfLines = input.nextInt(); // read the number of lines and store in variable
		System.out.println("Enter the number of ovals to be drawn :"); // ask the user for number of ovals
		numberOfOvals = input.nextInt();// read the number of ovals and store in variable
		System.out.println("Enter the number of rectangles to be drawn :"); // ask the user for number of rectangles
		numberOfRects = input.nextInt();// read the number of rectangles and store in variable
		shapes = new MyShape[numberOfLines + numberOfRects + numberOfOvals]; // initialise the array shapes of objects MyShape and size
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
		} // end for loop
    } // end DrawPanel constructor
	
	// method to remove the latest most shape from the container
	public void clearLastShape(shapeCount) {
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
		for(MyShape shape : shapes){
			shape.draw(g); // polymorphically call the draw method
		} // end for loop
	} // end method paintComponent
} // end class DrawPanel