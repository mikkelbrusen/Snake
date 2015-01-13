package view;

import controller.Controller;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Model;

public class View extends JFrame {
	


	private final MainPanel snakePanel;
	private final Model model;
	private final MainMenu mainMenu;
	private final OptionsMenu options;
	private final StartMenu startMenu;

	
	public View(Model model, Controller controller) {
            super();
            this.setTitle("Snake - the super, mega, awesome quest for epic awesomeness!");
            this.model = model;
            
            CardLayout cl = new CardLayout();
            
            
            
            
            snakePanel = new MainPanel(model.getDimension(),model);
            mainMenu = new MainMenu(controller);
            options = new OptionsMenu(controller);
            startMenu = new StartMenu(controller);
    		BoxLayout layout = new BoxLayout(options, BoxLayout.Y_AXIS);
    		this.getContentPane().add(startMenu, cl);
            this.getContentPane().add(mainMenu, cl);
            this.getContentPane().add(snakePanel, cl);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);
            this.setUndecorated(true);
            this.setFocusable(true);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(null);
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
        
        public void showHighScore(){
            JOptionPane.showMessageDialog(this, "Current Highscore is: " + model.getHighScore());
        }
}
