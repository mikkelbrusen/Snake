package view;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainMenu extends JMenuBar{

    public MainMenu(){
        JMenu menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_M);

        JMenuItem newGame = new JMenuItem("New game", 'N');
        newGame.setMnemonic(KeyEvent.VK_N);
        menu.add(newGame);

        JMenuItem highScores = new JMenuItem("Highscores", 'H');
        highScores.setMnemonic(KeyEvent.VK_H);
        menu.add(highScores);

        JMenuItem options = new JMenuItem("Options", 'O');
        options.setMnemonic(KeyEvent.VK_O);
        menu.add(options);

        this.add(menu);

        this.setVisible(true);
        this.setPreferredSize(new Dimension(20,20));
    }
}
