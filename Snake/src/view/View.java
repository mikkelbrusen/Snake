package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Model;
import controller.Controller;

public class View extends JFrame {

	private final MainPanel snakePanel;
	private final Model model;
	private final MainMenu mainMenu;
	private final OptionsMenu options;
	private final StartMenu startMenu;
	private final static JPanel panel = new JPanel();;
	public final static CardLayout cl = new CardLayout();

	public View(Model model, Controller controller) {
		super();

		this.setTitle("Snake - the super, mega, awesome quest for epic awesomeness!");
		this.model = model;
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

		toStart();
		this.add(panel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setFocusable(true);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(null);
		this.setVisible(true);

	}

	public void doAnnounce() {
		String m = "Game Over!\n" + "Your score is: " + model.getScore() + "\n"
				+ "High score is: " + model.getHighScore();
		if (model.getScore() > model.getHighScore())
			m += "\n You beat the high score!";
		JOptionPane.showMessageDialog(this, m);
	}

	public void showHighScore() {
		JOptionPane.showMessageDialog(this,	"Current Highscore is: " + model.getHighScore());
	}
	
	public static void toOptions() {
		cl.show(panel, "options");
	}
	
	public static void toStart() {
		cl.show(panel, "start");
	}
	
	public static void toGame() {
		cl.show(panel, "game");
	}

}
