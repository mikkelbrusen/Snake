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
	
	public View(Model model) {
            super();
            this.model = model;
            this.snakePanel = new MainPanel(model.getDimension(),model);
            
            
            this.getContentPane().add(snakePanel, BorderLayout.CENTER);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }
}
