package view;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;

import controller.Controller;

public class OptionsMenu extends JPanel{
	
	private Dimension size;
	
	public OptionsMenu(Controller controller){
		super();
		this.size = size;
		
		BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(box);
		this.setBorder(new EmptyBorder(50, 50, 50, 50));
		
		JLabel sizeLabel = new JLabel("Tracksize");
		sizeLabel.setAlignmentX(CENTER_ALIGNMENT);
		this.add(sizeLabel);
		
		//slider with numbers for tracksize
		JSlider trackSlider = new JSlider(JSlider.HORIZONTAL, 5, 100, 50);
		trackSlider.setPaintTicks(true);
		trackSlider.setPaintLabels(true);
		trackSlider.setMajorTickSpacing(25);
		trackSlider.setMinorTickSpacing(5);
		trackSlider.setSnapToTicks(true);
		
		trackSlider.setLabelTable(trackSlider.createStandardLabels(15));
		
		this.add(trackSlider, "Tracksize");
		
		this.add(Box.createRigidArea(new Dimension(0,50)));
		
		JLabel speedLabel = new JLabel("Speed");
		speedLabel.setAlignmentX(CENTER_ALIGNMENT);
		this.add(speedLabel);
		
		//slider with numbers for speed
		JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 10, 500, 100);
		speedSlider.setPaintTicks(true);
		speedSlider.setPaintLabels(true);
		speedSlider.setMajorTickSpacing(45);
		speedSlider.setMinorTickSpacing(15);
		speedSlider.setSnapToTicks(true);
		
		speedSlider.setLabelTable(speedSlider.createStandardLabels(45));
		
		this.add(speedSlider, "Speed");
		
		this.add(Box.createRigidArea(new Dimension(0,50)));
		
		JLabel characterLabel = new JLabel("Speed");
		characterLabel.setAlignmentX(CENTER_ALIGNMENT);
		this.add(characterLabel);
		
		// add a slider with icon label
	    JSlider characterSlider = new JSlider();
	    characterSlider.setPaintTicks(true);
	    characterSlider.setPaintLabels(true);
	    characterSlider.setSnapToTicks(true);
	    characterSlider.setMajorTickSpacing(20);
	    characterSlider.setMinorTickSpacing(20);

	    Dictionary<Integer, Component> labelTable = new Hashtable<Integer, Component>();

	    // add card images

	    labelTable.put(0, new JLabel(new ImageIcon("nine.gif")));
	    labelTable.put(20, new JLabel(new ImageIcon("ten.gif")));
	    labelTable.put(40, new JLabel(new ImageIcon("jack.gif")));
	    labelTable.put(60, new JLabel(new ImageIcon("queen.gif")));
	    labelTable.put(80, new JLabel(new ImageIcon("king.gif")));
	    labelTable.put(100, new JLabel(new ImageIcon("ace.gif")));

	    characterSlider.setLabelTable(labelTable);
	    this.add(characterSlider, "Icon labels");
		
	}
	

	  public static void main(String s[]) {
	    JFrame frame = new JFrame("Slider Example");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	  }
}
