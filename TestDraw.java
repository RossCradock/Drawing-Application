// Ross Cradock.
// Test program to display a DrawPanel full of random shapes.

import javax.swing.JFrame; // get the window frame from the swing framework
import java.awt.BorderLayout; // get border location methods from abstract window toolkit

public class TestDraw {
	public static void main(String args[]) {
		DrawFrame frame = new DrawFrame(); // create the frame to hold the panel
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 650); // set frame size
		frame.setVisible(true);
   } // end main
} // end class TestDraw
