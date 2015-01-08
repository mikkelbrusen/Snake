package view;

import java.awt.Dimension;

import javax.swing.JApplet;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MainMenu extends JMenuBar{

	public MainMenu(){
		JMenu menu = new JMenu("Menu");
		
        JMenuItem newGame = new JMenuItem("New game", 'N');
        newGame.setAccelerator(KeyStroke.getKeyStroke('N'));
        menu.add(newGame);
        
        JMenuItem highScores = new JMenuItem("Highscores", 'H');
        newGame.setAccelerator(KeyStroke.getKeyStroke('H'));
        menu.add(highScores);
        
        JMenuItem options = new JMenuItem("Options", 'O');
        newGame.setAccelerator(KeyStroke.getKeyStroke('O'));
        menu.add(options);
        
        this.add(menu);
        
        this.setVisible(true);
        this.setPreferredSize(new Dimension(20,20));

	}
}
