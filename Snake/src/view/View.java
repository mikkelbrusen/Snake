package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Model;

public class View extends JFrame {
	
	private MainPanel snakePanel;
	private Model model;
	
	public View() {
		super();
		this.snakePanel = new MainPanel(new Dimension(700,400));
		this.getContentPane().add(snakePanel, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		View view = new View();
	}
    
}
