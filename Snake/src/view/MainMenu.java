package view;

import controller.Controller;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JApplet;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import model.Objects;

public class MainMenu extends JMenuBar implements ActionListener{

    Controller controller;
	public MainMenu(Controller controller){
            this.controller = controller;
            JMenu menu = new JMenu("Menu");
            menu.setMnemonic(KeyEvent.VK_M);
		
                JMenuItem newGame = new JMenuItem("New game", 'N');
                newGame.setMnemonic(KeyEvent.VK_N);
                newGame.addActionListener(this);
                menu.add(newGame);

                JMenuItem highScores = new JMenuItem("Highscores", 'H');
                highScores.setMnemonic(KeyEvent.VK_H);
                highScores.addActionListener(this);
                menu.add(highScores);

                JMenu options = new JMenu("Options");
                options.setMnemonic(KeyEvent.VK_O);
                menu.add(options);

                    JCheckBoxMenuItem AISelect = new JCheckBoxMenuItem("Artificial Intelligence");
                    AISelect.setAccelerator(KeyStroke.getKeyStroke(
                        KeyEvent.VK_1, ActionEvent.ALT_MASK));
                    AISelect.addActionListener(this);
                    options.add(AISelect);
                    
                    JMenuItem speedUp = new JMenuItem("SpeedUp");
                    speedUp.setAccelerator(KeyStroke.getKeyStroke(
                        KeyEvent.VK_2, ActionEvent.ALT_MASK));
                    speedUp.addActionListener(this);
                    options.add(speedUp);
                    
                    JMenuItem speedDown = new JMenuItem("SpeedDown");
                    speedDown.setAccelerator(KeyStroke.getKeyStroke(
                        KeyEvent.VK_3, ActionEvent.ALT_MASK));
                    speedDown.addActionListener(this);
                    options.add(speedDown);

            this.add(menu);
        
        this.setVisible(true);
        this.setPreferredSize(new Dimension(20,20));

	}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "New game"){
            controller.doCmd(Objects.RESET_GAME);
        }
        else if(e.getActionCommand() == "Highscores"){
            controller.doCmd(Objects.SHOW_HIGHSCORES);
        }
        else if(e.getActionCommand() == "Artificial Intelligence"){
            controller.doCmd(Objects.ENABLE_AI);
        }
        else if(e.getActionCommand() == "SpeedUp"){
            controller.doCmd(Objects.SPEED_UP);
        }
        else if(e.getActionCommand() == "SpeedDown"){
            controller.doCmd(Objects.SPEED_DOWN);
        }
    }
}
