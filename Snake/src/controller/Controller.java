package controller;

import AI.AI;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import model.Model;
import view.*;


public class Controller {
        private final static int INTERVAL = 200;
	private Model model;
	private final View view;
        private boolean useAI;
        private AI ai;
	
	public Controller(Dimension dimension,String fileName) {
            this.model = new Model(dimension,fileName);
            this.view = new View(model);
            this.ai = new AI(model);
            this.useAI = true;
          
            Timer timer = new Timer(INTERVAL, (ActionEvent e) -> {
                if(model.isGameOver()){
                    view.doAnnounce();
                    model.doReset();
                }
                else if(model.isPaused()) {
                }
                else if(useAI){
                    try {
                        ai.runAI();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
