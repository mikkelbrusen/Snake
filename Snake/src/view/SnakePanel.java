package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class SnakePanel extends JPanel {
	
	private Dimension size;
	
	public SnakePanel(Dimension size) {
		super();
		this.size = size;
		this.setLayout(new GridLayout(size.height,size.width));
		this.setBackground(Color.WHITE);
		
		
		
	}
	
}
