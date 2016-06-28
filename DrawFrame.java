/*
 Ross Cradock.
 
*/

import java.awt.BorderFrame;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class DrawFrame extends JFrame {
	private JButton undoButton;
	private JButton clearButton;
	private JList colorComboBox;
	private JList shapeComboBox;
	private JCheckBox fillCheckBox;
	private JLabel status;
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
		setFrameLayout(new BorderLayout());
		DrawPanel panel = new DrawPanel(status);
		
		undoButton = new JButton("Undo");
		add(undoButton);
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				panel.clearLastShape();
			}
		});
		
		clearButton = new JButton("Clear");
		add(clearButton);
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				panel.clearDrawing();
			}
		});
		
		colorComboBox = new JList(colorNames);
		colorComboBox.setVisibleRowCount(5);
		colorComboBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(new JScrollPane(colorComboBox));
		colorComboBox.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				panel.setCurrentColor(colors[colorComboBox.getSelectedIndex()]);
			}
		});
		
		shapeComboBox = new JList(shapeNames);
		shapeComboBox.setVisibleRowCount(3);
		shapeComboBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(new JScrollPane(shapeComboBox));
		shapeComboBox.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				panel.setCurrentshape(shapeComboBox.getSelectedIndex());
			}
		});
		
		fillCheckBox = new JCheckBox("Filled");
		fillCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if(fillCheckBox.isSelected) {
					panel.setShapeFill(true);
				} else {
					panel.setShapeFill(false);
				}
			}
		});
	}
}