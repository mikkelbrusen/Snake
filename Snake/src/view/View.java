package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Model;
import model.Objects;
import controller.Controller;

import java.util.LinkedList;

import model.HighScore;

public class View extends JFrame {
	private final MainPanel snakePanel;
	private final Model model;
	private final Controller controller;
	private final MainMenu mainMenu;
	private final OptionsMenu options;
	private final StartMenu startMenu;

	private final static JPanel panel = new JPanel();;
	public final static CardLayout cl = new CardLayout();


	public View(Model model, Controller controller) {
		super();

		this.setTitle("Snake - the super, mega, awesome quest for epic awesomeness!");
		this.model = model;
		this.controller = controller;
		snakePanel = new MainPanel(model.getDimension(), model);
		mainMenu = new MainMenu(controller);
		options = new OptionsMenu(controller);
		startMenu = new StartMenu(controller);
		// BoxLayout layout = new BoxLayout(options, BoxLayout.Y_AXIS);


		this.getContentPane().add(startMenu, BorderLayout.CENTER);
		this.getContentPane().add(mainMenu, BorderLayout.NORTH);
		this.getContentPane().add(snakePanel, BorderLayout.CENTER);

		panel.setLayout(cl);
		panel.add(startMenu, "start");
		panel.add(snakePanel, "game");
		panel.add(options, "options");

		controller.doCmd(Objects.PAUSE_GAME);
		toStart();
		this.add(panel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setFocusable(true);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(null);
		this.setVisible(true);

	}


	public void doAnnounce() {
            if(!model.getHasUsedAI()){
                if(model.getScore() > model.getLowestHighScore()){
                    String m = "Congratulations! You beat a high score!\n"
                            + " Please enter your name: ";
                    String name = JOptionPane.showInputDialog(m);
                    model.setNewHighScore(name);
                }
                LinkedList<HighScore> highScores = model.getHighScores();
                String m = "Current highscores:\n";
                for (int i = 0; i < highScores.size(); i++){
                    String n = "" + highScores.get(i).getName();
                    
                    if(!(n == null)){
                        n += " : ";
                        n += highScores.get(i).getScore();
                    }else
                        n = "No Name";
                    m += n + "\n";
                }
                JOptionPane.showConfirmDialog(rootPane, m);
                
            }
	}



	public void showHighScore() {
//		JOptionPane.showMessageDialog(this,	"Current Highscore is: " + model.getHighScore());
	}
	
	public void toOptions() {
		cl.show(panel, "options");
	}
	
	public void toStart() {
		cl.show(panel, "start");
	}
	
	public void toGame() {
		cl.show(panel, "game");
		model.setPaused(false);
	}


}
