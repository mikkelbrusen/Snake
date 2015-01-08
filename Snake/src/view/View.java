package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;






import javax.swing.KeyStroke;

import model.Model;

public class View extends JFrame {
	
	private MainPanel snakePanel;
	private Model model;
	private MainMenu mainMenu;
	
	public View(Model model) {
            super();
            this.model = model;
            this.snakePanel = new MainPanel(model.getDimension(),model);
            this.mainMenu = new MainMenu();
            this.getContentPane().add(mainMenu, BorderLayout.NORTH);
            this.getContentPane().add(snakePanel, BorderLayout.CENTER);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            
        }
	public void doAnnounce() {
		String m = "Game Over";
		JOptionPane.showMessageDialog(this, m);
		
	}
}
