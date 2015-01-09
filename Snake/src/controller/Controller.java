package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import model.Model;
import view.*;


public class Controller {
        private final static int INTERVAL = 100;
	private Model model;
	private final View view;
	
	public Controller(Dimension dimension,String fileName) {
            this.model = new Model(dimension,fileName);
            this.view = new View(model);
          
            Timer timer = new Timer(INTERVAL, (ActionEvent e) -> {
                if(model.isGameOver()){
                    view.doAnnounce();
                    model.doReset();
                }
                else
                    model.moveSnake();
                view.repaint();
            });
                
            timer.start();

            KeyboardListener d = new KeyboardListener(model,view);
            view.addKeyListener(d);
            
	}
}
