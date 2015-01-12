package view;

import controller.Controller;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Model;

public class View extends JFrame {
	

	private MainPanel snakePanel;
	private Model model;
	private MainMenu mainMenu;
	private OptionsPanel options;
	
	public View(Model model,Controller controller) {
            super();
            this.setTitle("Snake - the super, mega, awesome quest for epic awesomeness!");
            this.model = model;
            snakePanel = new MainPanel(model.getDimension(),model);
            mainMenu = new MainMenu(controller);
            options = new OptionsPanel(new Dimension(640,360));
    		BoxLayout layout = new BoxLayout(options, BoxLayout.Y_AXIS);
            this.getContentPane().add(mainMenu, BorderLayout.NORTH);
            this.getContentPane().add(snakePanel, BorderLayout.CENTER);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);
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
        
        public void showHighScore(){
            JOptionPane.showMessageDialog(this, "Current Highscore is: " + model.getHighScore());
        }
}
