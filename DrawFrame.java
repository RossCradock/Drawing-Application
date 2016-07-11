/*
 Ross Cradock.
 This program creates the frame where the drawing panel will be held
 It also has a toolbar which allows the user to chose the type of shape,
 the colour of the shape and whether or not the shape is filled
*/

import java.awt.BorderLayout; // import the layout scheme which will locate elements to top, bottom, left, right or centre
import javax.swing.JButton; // import the button resource from the swing framework
import javax.swing.JList; // import the list resource from the swing framework
import javax.swing.JCheckBox; // import the checkbox resource from the swing framework
import javax.swing.JLabel; // import the label resource from the swing framework for the status bar
import javax.swing.JScrollPane; // import the scroll resource from the swing framework to allow the user to scroll through the list
import javax.swing.JFrame; // import the frame resource from the swing framework to hold the drawing panel
import javax.swing.JPanel; // import the panel resource from the swing framework to draw upon
import java.awt.Color; // import color objects
import java.awt.event.ActionListener; // import an event listener for the buttons
import javax.swing.event.ListSelectionListener; // import an event listener for the shape and colour lists
import java.awt.event.ActionEvent; // import the event object for the button
import javax.swing.event.ListSelectionEvent; // import the event object for the lists
import java.awt.event.ItemListener; // import an event listener for the filled checkbox
import java.awt.event.ItemEvent; // import the event object for the checkbox
import javax.swing.ListSelectionModel; // import premade list selection models for the list

public class DrawFrame extends JFrame {
	private JButton undoButton; // declare the undo button
	private JButton clearButton; // declare the clear button
	private JList colorList; // declare the colors list
	private JList shapeList; // declare the shapes list
	private JCheckBox fillCheckBox; // declare the checkbox for filled
	private final String colorNames[] = {"Black", "Blue", "Cyan",
			"Dark Gray", "Gray", "Green", "Light Gray", "Magenta",
			"Orange", "Pink", "Red", "White", "Yellow"};  // declare the string array of the colour names
	private final Color colors[] = {Color.BLACK, Color.BLUE, Color.CYAN,
			Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY,
			Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED,
			Color.WHITE, Color.YELLOW}; // declare the actual color objects
	private final String shapeNames[] = {"Line", "Rectangle", "Oval"};  // declare the shape names
	
	// DrawFrame constructor
	public DrawFrame() {
		super("Java Drawing"); // call the superclass constructor and pass the title string to it
		JPanel toolbar = new JPanel(); // initialise a subpanel to the DrawPanel so all the elements of the toolbar can be shown
		setLayout(new BorderLayout()); // set the layout scheme
		
		JLabel status = new JLabel(""); // initialise the status bar and set it's text with an empty string
		add(status, BorderLayout.SOUTH); // add the status bar to the panel at the bottom of it
		DrawPanel panel = new DrawPanel(status); // create a new DrawPanel object and pass the status bar argument as JLabel
		add(panel, BorderLayout.CENTER); // add the DrawPanel object to the frame
		
		undoButton = new JButton("Undo"); // initialise the undo button
		toolbar.add(undoButton, BorderLayout.NORTH); // add the undo button to the sub panel toolbar
		undoButton.addActionListener(new ActionListener() { // add the listener which will call the anonymous innerclass ActionListener
			public void actionPerformed(ActionEvent event) { // method that is called upon the button being clicked
				panel.clearLastShape(); // call the DrawPanel's public method to clear the previous shape from the panel
			} // end actionPerformed method
		}); // end ActionListener innerclass
		
		clearButton = new JButton("Clear"); // initialise the clear button
		toolbar.add(clearButton, BorderLayout.NORTH); // add the clear button to the sub panel toolbar
		clearButton.addActionListener(new ActionListener() { // add the listener which will call the anonymous innerclass ActionListener
			public void actionPerformed(ActionEvent event) { // method that is called upon the button being clicked
				panel.clearDrawing(); // call the DrawPanel's public method to clear all shapes from the panel
			} // end actionPerformed method
		}); // end ActionListener innerclass
		
		colorList = new JList(colorNames); // initialise the color list
		colorList.setVisibleRowCount(1); // set the number of list items that can be seen
		colorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // set the type of selection model
		toolbar.add(new JScrollPane(colorList), BorderLayout.NORTH);  // add the color list to the sub panel toolbar
		colorList.addListSelectionListener(new ListSelectionListener() { // add the listener which will call the anonymous innerclass ListSelectionListener
			public void valueChanged(ListSelectionEvent event) { // method that is called upon the list selection changing
				panel.setCurrentColor(colors[colorList.getSelectedIndex()]); // call the DrawPanel's public set method and pass the Color based on the array index
			} // end valueChanged method
		}); // end ListSelectionListener innerclass
		
		shapeList = new JList(shapeNames); // initialise the color list
		shapeList.setVisibleRowCount(1); // set the number of list items that can be seen
		shapeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // set the type of selection model
		toolbar.add(new JScrollPane(shapeList), BorderLayout.NORTH);  // add the shape list to the sub panel toolbar
		shapeList.addListSelectionListener(new ListSelectionListener() { // add the listener which will call the anonymous innerclass ListSelectionListener
			public void valueChanged(ListSelectionEvent event) { // method that is called upon the list selection changing
				int type = shapeList.getSelectedIndex(); // initialise the shape type integer based on the array index
				panel.setShapeType(type); // call the DrawPanel's public set method and pass the shape type
			} // end valueChanged method
		}); // end ListSelectionListener innerclass
		
		fillCheckBox = new JCheckBox("Filled"); // initialise the filled checkbox
		toolbar.add(fillCheckBox, BorderLayout.NORTH);  // add the filled checkbox to the sub panel toolbar
		fillCheckBox.addItemListener(new ItemListener() { // add the listener which will call the anonymous innerclass ItemListener
			public void itemStateChanged(ItemEvent event) { // method that is called upon the checkbox changing
				if(fillCheckBox.isSelected()) { // check if the checkbox is checked or not
					panel.setShapeFill(true); // if the checkbox is checked, call the DrawPanel's public set method and pass the true boolean
				} else {
					panel.setShapeFill(false); // if the checkbox is not checked, call the DrawPanel's public set method and pass the false boolean
				}
			} // end itemStateChanged method
		}); // end ItemListener innerclass
		
		add(toolbar, BorderLayout.NORTH); // add the toolbar to the JPanel
	}
}