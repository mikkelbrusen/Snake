package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Model;

public class View extends JFrame {
	
	private JPanel snakePanel;
	Model model;
	
	public View(Model model) {
		
		super();
		this.snakePanel = new JPanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	
    
}
