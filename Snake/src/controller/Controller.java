package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import model.Model;
import model.Objects;
import view.*;


public class Controller {
        private int INTERVAL = 128;
	private final Model model;
	private final View view;
        Timer timer;
	
	public Controller(Dimension dimension,String fileName) {
            this.model = new Model(dimension,fileName);
            this.view = new View(model,this);
          
            newTimer();   
            timer.start();

            KeyboardListener d = new KeyboardListener(model,view,this);
            view.addKeyListener(d);
	}
        
        private void newTimer(){
            this.timer = new Timer(INTERVAL, (ActionEvent e) -> {
            if(model.isGameOver()){
                if(!model.getUseAI()){
                    view.doAnnounce();
                    model.doReset();
                }else{
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    model.doReset();
                }
            }
            else if(model.isPaused()) {
            }
            else
                try {
                    model.moveSnake();
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
                view.repaint();
            });
        }
        
        public void doCmd(Objects o){
            switch(o){
                case RESET_GAME:
                    model.doReset();
                    break;
                case SHOW_HIGHSCORES:
                    view.showHighScore();
                    break;
                case ENABLE_AI:
                    model.setUseAI(!model.getUseAI());
                    break;
                case SPEED_UP:
                    timer.stop();
                    this.INTERVAL *= 0.5;
                    newTimer();
                    timer.start();
                    break;
                case SPEED_DOWN:
                    timer.stop();
                    if(INTERVAL == 0)
                        INTERVAL = 1;
                    else
                        this.INTERVAL *= 2;
                    newTimer();
                    timer.start();
                    break;
                case OPTIONS:
                	view.toOptions();
                	model.setPaused(true);
                	break;
                case EXIT_GAME:
                	System.exit(0);
                	break;
                case PAUSE_GAME:
                	model.setPaused(true);
                	break;
                case START_MENU:
                	view.toStart();
                	model.setPaused(true);
                	break;
                case START_GAME:
                	view.toGame();
                	model.setPaused(false);
                	break;
            }
        }
}
