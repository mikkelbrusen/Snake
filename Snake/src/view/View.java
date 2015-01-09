package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
		String m = "Game Over!\n Your score was: " + model.getScore();
		JOptionPane.showMessageDialog(this, m);
	}
}
