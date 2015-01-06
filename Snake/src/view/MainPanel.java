package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
	
	private Dimension size;
	
	public MainPanel(Dimension size) {
		super();
		this.size = size;
		this.setLayout(new GridLayout(size.height,size.width));
		this.setBackground(Color.WHITE);
		this.setPreferredSize(size);
		
		
		
	}
	
}
