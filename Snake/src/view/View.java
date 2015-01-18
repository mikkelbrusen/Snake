package view;

import controller.Controller;
import model.Enumerators;
import model.HighScore;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public final class View extends JFrame {
	private final static JPanel panel;
	private final static CardLayout cl;

	static {
		cl = new CardLayout();
		panel = new JPanel();
	}
	private final GamePanel snakePanel;
	private final Model model;
	private final Controller controller;
	private final OptionsMenu options;


	public View(Model model, Controller controller) {
		super();

		this.setTitle("Atomic Bomber Snake - the super, mega, awesome quest for epic awesomeness!");
		this.model = model;
		this.controller = controller;
		snakePanel = new GamePanel(model.getDimension(), model);
		options = new OptionsMenu(controller);
		StartMenu startMenu = new StartMenu(controller);

		this.getContentPane().add(startMenu, BorderLayout.CENTER);
		this.getContentPane().add(snakePanel, BorderLayout.CENTER);

		panel.setLayout(cl);
		panel.add(startMenu, "start");
		panel.add(snakePanel, "game");
		panel.add(options, "options");

		controller.doCmd(Enumerators.PAUSE_GAME);
		displayStartMenu();
		this.add(panel);

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setFocusable(true);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//this.pack();
		this.setVisible(true);

	}

	public void doAnnounce() {
		if (!model.getHasUsedAI()) {
			askForNameIfNewHighScore();
			String m = generateHighScores();
			Object stringArray[] = {"New Game", "Main Menu", "Exit Game"};
			displayHighScores(m, stringArray);
		}
	}

	private void displayHighScores(String m, Object[] stringArray) {
		int confirmDia = JOptionPane.showOptionDialog(rootPane, m, "Please select an option",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, stringArray, stringArray[0]);
		if (confirmDia == JOptionPane.YES_OPTION) {
			controller.doCmd(Enumerators.RESET_GAME);
		} else if (confirmDia == JOptionPane.NO_OPTION) {
			controller.doCmd(Enumerators.START_MENU);
			controller.doCmd(Enumerators.PAUSE_GAME);
		} else {
			controller.doCmd(Enumerators.EXIT_GAME);
		}
	}

	private String generateHighScores() {
		LinkedList<HighScore> highScores = model.getHighScores();
		String m = "Current high scores:\n";
		for (HighScore highScore : highScores) {
			String n = highScore.getName();
			if (n == null)
				n = "No Name";
			else if (n.equals(""))
				n = "No Name";
			n += " : ";
			n += highScore.getScore();
			m += n + "\n";
		}
		return m;
	}

	private void askForNameIfNewHighScore() {
		if (model.getScore() > model.getLowestHighScore()) {
			String m = "Congratulations! You beat a high score!\n"
					+ " Please enter your name: ";
			String name = JOptionPane.showInputDialog(m);
			model.setNewHighScore(name);
		}
	}

	public void showPause(boolean b) {
		snakePanel.showPause(b);
	}

	public void displayOptionsMenu() {
		cl.show(panel, "options");
	}

	public void displayStartMenu() {
		cl.show(panel, "start");
	}

	public void displayGame() {
		cl.show(panel, "game");
		model.setPaused(false);
	}

	public void changeDimension(Dimension dimension){
		snakePanel.setDimension(dimension);
	}
	
	public int getSpeed() {
		return options.getSpeed();
	}
}
