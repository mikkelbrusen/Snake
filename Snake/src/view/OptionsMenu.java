package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Enumerators;
import controller.Controller;

class OptionsMenu extends JPanel implements ActionListener {

	private final Controller controller;
	private final BufferedImage[] images;
	private final String[] tracks = {"16x9_noAI_empty", "16x9_straightLine", "32x18_snake1", "32x18_snake2", 
			"32x18_snake3", "32x18_withWalls", "48x27_noAI_withWormHoles", "48x27_withComplexWalls", "48x27_withWalls"};
	private String trackName;
	private int value;
	
	public OptionsMenu (Controller controller) {
		super();
		this.controller = controller;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		
		BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(box);
		this.setBorder(new EmptyBorder((int) (screenHeight * 0.1), (int) (screenWidth * 0.3), (int) (screenHeight * 0.1), (int) (screenWidth * 0.3)));

		//Loading images of tracks
		images = new BufferedImage[tracks.length];
		Integer[] intTracks = new Integer[tracks.length];

		for (int i = 0; i < tracks.length; i++) {
			intTracks[i] = i;
			images[i] = createBufferedImage(tracks[i] + ".png");
		}

		//Add combobox
		JComboBox trackList = new JComboBox(intTracks);
		ComboBoxRenderer renderer = new ComboBoxRenderer(this);
		renderer.setPreferredSize(new Dimension(540, 180));
		trackList.setRenderer(renderer);
		trackList.setMaximumRowCount(3);
		trackList.addActionListener(this);
		

		JPanel trackPanel = new JPanel();
		trackPanel.add(trackList);

		this.add(trackPanel);
		
		JLabel speedLabel = new JLabel("Speed");
		speedLabel.setAlignmentX(CENTER_ALIGNMENT);
		speedLabel.setFont(new Font("Back", Font.PLAIN, 24));
		this.add(speedLabel);
		
		//slider with numbers for speed
		int majorTick = 70;
		int minorTick = 10;
		JSlider speedSlider = addSlider(majorTick, minorTick);
		speedSlider.setLabelTable(speedSlider.createStandardLabels(70));
		speedSlider.setValue(128);
		speedSlider.addChangeListener(new ChangeListener() {
			private int value;

			@Override
			public void stateChanged(ChangeEvent evt) {
				JSlider slider = (JSlider) evt.getSource();
				if (!slider.getValueIsAdjusting()) {
					value = slider.getValue();
					double d = (1/(double)value)*10000;
					String sValue = Integer.toString((int)d);
					controller.doCmd(Enumerators.SET_SPEED, sValue);
				}
			}
		});
		this.value = speedSlider.getValue();
		
		this.add(speedSlider, "Speed");
		
		this.add(Box.createRigidArea(new Dimension(0,50)));

		//Add Checkbox for AI
		JCheckBox enableAI = new JCheckBox("Enable AI");
		enableAI.setFont(new Font("Back", Font.PLAIN, 24));
		enableAI.setAlignmentX(CENTER_ALIGNMENT);
		enableAI.setPreferredSize(new Dimension(50, 50));
		enableAI.addActionListener(this);
		this.add(enableAI);

		this.add(Box.createRigidArea(new Dimension(0, 50)));
		
		JButton back = new JButton("Back");
		back.setFont(new Font("Back", Font.PLAIN, 24));
		back.setAlignmentX(CENTER_ALIGNMENT);
		back.addActionListener(this);
		this.add(back);
	}

	private static BufferedImage createBufferedImage(String path) {
		BufferedImage images;
		try {
			images = ImageIO.read(new File(path));
			return images;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	JSlider addSlider(int major, int minor) {
		JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 10, 500, 100);
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
			controller.doCmd(Enumerators.START_MENU);
			break;
			case "Enable AI":
				controller.doCmd(Enumerators.ENABLE_AI);
				break;
			case "comboBoxChanged":
				JComboBox trackList = (JComboBox) e.getSource();
				int trackNum = (int) trackList.getSelectedItem();
				this.trackName = tracks[trackNum];
				controller.doCmd(Enumerators.CHANGE_TRACK, this.trackName);
				break;
		}
	}
	
	

	public BufferedImage[] getImages() {
		return images;
	}

	public String[] getTracks() {
		return tracks;
	}
	
	public int getSpeed() {
		return value;
	}
}
