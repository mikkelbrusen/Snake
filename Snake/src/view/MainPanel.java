package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
	
	public static final int SCALE = 25;
	
	private Box[][] fields;
	private Dimension size;
	
	public MainPanel(Dimension size) {
		super();
		this.size = size;
		this.setLayout(new GridLayout(size.height,size.width));
		this.setBackground(Color.WHITE);
		this.setPreferredSize(size);
		this.setOpaque(true);
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(0, 0, SCALE, SCALE);
	}

}
