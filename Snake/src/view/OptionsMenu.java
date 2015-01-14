package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;

import model.Objects;
import controller.Controller;

public class OptionsMenu extends JPanel implements ActionListener {
	
	private Dimension size;
	private Controller controller;
	
	public OptionsMenu (Controller controller) {
		super();
		this.size = size;
		this.controller = controller;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		
		BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(box);
		this.setBorder(new EmptyBorder((int) (screenHeight*0.3), (int) (screenWidth*0.3), (int) (screenHeight*0.3), (int) (screenWidth*0.3)));
		
		JLabel sizeLabel = new JLabel("Tracksize");
		sizeLabel.setAlignmentX(CENTER_ALIGNMENT);
		this.add(sizeLabel);
		
		//slider with numbers for tracksize
		int majorTick = 25;
		int minorTick = 5;
		JSlider trackSlider = addSlider(JSlider.HORIZONTAL, 5, 100, 50, majorTick, minorTick);
		
		trackSlider.setLabelTable(trackSlider.createStandardLabels(15));
		
		this.add(trackSlider, "Tracksize");
		
		this.add(Box.createRigidArea(new Dimension(0,50)));
		
		JLabel speedLabel = new JLabel("Speed");
		speedLabel.setAlignmentX(CENTER_ALIGNMENT);
		this.add(speedLabel);
		
		//slider with numbers for speed
		majorTick = 45;
		minorTick = 15;
		JSlider speedSlider = addSlider(JSlider.HORIZONTAL, 10, 500, 100, majorTick, minorTick);
		
		speedSlider.setLabelTable(speedSlider.createStandardLabels(45));
		
		this.add(speedSlider, "Speed");
		
		this.add(Box.createRigidArea(new Dimension(0,50)));

		JButton back = new JButton("Back");
		back.setFont(new Font("Back", Font.PLAIN, 24));
		back.setAlignmentX(CENTER_ALIGNMENT);
		back.addActionListener(this);
		this.add(back);
		
		this.add(Box.createRigidArea(new Dimension(0,50)));
		
		//Add Checkbox for AI
		JCheckBox enableAI = new JCheckBox("Enable AI");
		enableAI.setFont(new Font("Back", Font.PLAIN, 24));
		enableAI.setAlignmentX(CENTER_ALIGNMENT);
		enableAI.addActionListener(this);
		this.add(enableAI);
	}

	public JSlider addSlider(int a, int b, int c, int d, int major, int minor) {
		JSlider slider = new JSlider(a, b, c, d);
		slider.setPaintTicks(true);
	    slider.setPaintLabels(true);
	    slider.setSnapToTicks(true);
	    slider.setMajorTickSpacing(major);
		slider.setMinorTickSpacing(minor);
		return slider;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
		case "Back":
			controller.doCmd(Objects.START_MENU);
			break;
		case "Enable AI":
			controller.doCmd(Objects.ENABLE_AI);
			break;
		}
	}
}
