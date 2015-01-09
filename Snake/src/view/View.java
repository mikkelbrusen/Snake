package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Model;

public class View extends JFrame {
	
	private final MainPanel snakePanel;
	private final Model model;
	private final MainMenu mainMenu;
	
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
		String m = "Game Over!\n"
                        + "Your score is: " + model.getScore() + "\n"
                        + "High score is: " + model.getHighScore();
                if(model.getScore() > model.getHighScore())
                    m += "\n You beat the high score!";
		JOptionPane.showMessageDialog(this, m);
	}
}
