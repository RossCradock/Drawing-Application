// Ross Cradock.
// Test program to display a DrawPanel full of random shapes.

import javax.swing.JFrame; // get the window frame from the swing frameworkframework
import javax.swing.JLabel; // get the labeling methods from the swing framework
import java.awt.BorderLayout; // get border location methods from abstract window toolkit

public class TestDraw {
	public static void main(String args[]) {
		DrawPanel panel = new DrawPanel();  //create the panel to draw on    
		JFrame frame = new JFrame(); // create the frame to hold the panel
		JLabel statusLabel = new JLabel(panel.status());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(statusLabel, BorderLayout.SOUTH); // add the label to the bottom
		frame.add(panel);
		frame.setSize(600, 650); // set frame size to 600 pixels by 650 pixels to allow for shape positions and title frame
		frame.setVisible(true);
   } // end main
} // end class TestDraw
