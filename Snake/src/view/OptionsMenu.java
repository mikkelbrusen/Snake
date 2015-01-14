package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;

import model.Objects;
import controller.Controller;

public class OptionsMenu extends JPanel implements ActionListener {

	private Controller controller;
	BufferedImage[] images;
	private String[] tracks = {"16x9_noAI_empty.png","16x9_straightLine.png","32x18_withWalls.png","48x27_noAI_withWormHoles.png",
			"48x27_withComplexWalls.png","48x27_withWalls.png"};
	
	public OptionsMenu (Controller controller) {
		super();
		this.controller = controller;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		
		BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(box);
		this.setBorder(new EmptyBorder((int) (screenHeight*0.1), (int) (screenWidth*0.3), (int) (screenHeight*0.1), (int) (screenWidth*0.3)));
		
		//Loading images of tracks
		images = new BufferedImage[tracks.length];
		Integer[] intTracks = new Integer[tracks.length];
		
		for (int i = 0; i < tracks.length; i++) {
			intTracks[i] = new Integer(i);
			images[i] = createBufferedImage(tracks[i]);
//			if (images[i] != null) {
//				images[i].setDescription(new ImageIcon(tracks[i]));
//			}
		}
		
		//Add combobox
		JComboBox trackList = new JComboBox(intTracks);
		ComboBoxRenderer renderer = new ComboBoxRenderer(this);
		renderer.setPreferredSize(new Dimension(320,90));
		trackList.setRenderer(renderer);
		trackList.setMaximumRowCount(3);
		
		this.add(trackList);
		
		this.add(Box.createRigidArea(new Dimension(0,50)));
		
		JLabel speedLabel = new JLabel("Speed");
		speedLabel.setAlignmentX(CENTER_ALIGNMENT);
		this.add(speedLabel);
		
		//slider with numbers for speed
		int majorTick = 45;
		int minorTick = 15;
		JSlider speedSlider = addSlider(JSlider.HORIZONTAL, 10, 500, 100, majorTick, minorTick);
		
		speedSlider.setLabelTable(speedSlider.createStandardLabels(45));
		
		this.add(speedSlider, "Speed");
		
		this.add(Box.createRigidArea(new Dimension(0,50)));
		
		JLabel characterLabel = new JLabel("Speed");
		characterLabel.setAlignmentX(CENTER_ALIGNMENT);
		this.add(characterLabel);
		

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
	
	protected static BufferedImage createBufferedImage(String path) {
		BufferedImage images;
		try {
			System.out.println(path);
			images = ImageIO.read(new File(path));
			return images;
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public BufferedImage[] getImages(){
		return images;
	}
	
	public String[] getTracks() {
		return tracks;
	}
}
