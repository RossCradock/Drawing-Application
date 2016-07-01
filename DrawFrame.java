/*
 Ross Cradock.
 
*/

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.ListSelectionModel;

public class DrawFrame extends JFrame {
	private JButton undoButton;
	private JButton clearButton;
	private JList colorComboBox;
	private JList shapeComboBox;
	private JCheckBox fillCheckBox;
	private final String colorNames[] = {"Black", "Blue", "Cyan",
			"Dark Gray", "Gray", "Green", "Light Gray", "Magenta",
			"Orange", "Pink", "Red", "White", "Yellow"};
	private final Color colors[] = {Color.BLACK, Color.BLUE, Color.CYAN,
			Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY,
			Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED,
			Color.WHITE, Color.YELLOW};
	private final String shapeNames[] = {"Line", "Rectangle", "Oval"};
	
	public DrawFrame() {
		super("Java Drawing");
		JPanel toolbar = new JPanel();
		setLayout(new BorderLayout());
		JLabel status = new JLabel("update");
		add(status, BorderLayout.SOUTH);
		DrawPanel panel = new DrawPanel(status);
		add(panel, BorderLayout.CENTER);
		undoButton = new JButton("Undo");
		toolbar.add(undoButton, BorderLayout.NORTH);
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				panel.clearLastShape();
			}
		});
		
		clearButton = new JButton("Clear");
		toolbar.add(clearButton, BorderLayout.NORTH);
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				panel.clearDrawing();
			}
		});
		
		colorComboBox = new JList(colorNames);
		colorComboBox.setVisibleRowCount(1);
		colorComboBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		toolbar.add(new JScrollPane(colorComboBox), BorderLayout.NORTH);
		colorComboBox.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				panel.setCurrentColor(colors[colorComboBox.getSelectedIndex()]);
				System.out.printf("color set in drawFrame: %s\n", colors[colorComboBox.getSelectedIndex()].toString());
				status.setText(String.format("color(%d), shape(%d)", colorComboBox.getSelectedIndex(), shapeComboBox.getSelectedIndex())); // update the label with the mouse coordinates
			}
		});
		
		shapeComboBox = new JList(shapeNames);
		shapeComboBox.setVisibleRowCount(1);
		shapeComboBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		toolbar.add(new JScrollPane(shapeComboBox), BorderLayout.NORTH);
		shapeComboBox.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				int type = shapeComboBox.getSelectedIndex();
				panel.setShapeType(type);
			}
		});
		
		fillCheckBox = new JCheckBox("Filled");
		toolbar.add(fillCheckBox, BorderLayout.NORTH);
		fillCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if(fillCheckBox.isSelected()) {
					panel.setShapeFill(true);
				} else {
					panel.setShapeFill(false);
				}
			}
		});
		add(toolbar, BorderLayout.NORTH);
	}
}