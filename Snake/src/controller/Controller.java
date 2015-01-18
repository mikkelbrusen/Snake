package controller;

import model.Enumerators;
import model.Model;
import org.omg.CORBA.INV_FLAG;
import view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller {
    private final Model model;
    private final View view;
    private int INTERVAL;
    private Timer timer;

    public Controller(Dimension dimension, String fileName) {
    	
        this.model = new Model(dimension,fileName);
        this.view = new View(model,this);

        if (INTERVAL == 0) INTERVAL = 150;
        newTimer();
        timer.start();

        KeyboardListener d = new KeyboardListener(model,view,this);
        view.addKeyListener(d);
	}
        
    private void newTimer(){
        this.timer = new Timer(INTERVAL, (ActionEvent e) -> {
            if (model.getGameOver()) {
                if (model.isNotUsingAI()) {
                view.doAnnounce();
                model.doReset();
                } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                model.doReset();
            }
        }
            model.moveSnake();
            view.repaint();
        });
    }

    void changeDimension(){
        view.changeDimension(model.getDimension());
    }
    
    public void doCmd(Enumerators o, String s) {
        switch (o) {
            case CHANGE_TRACK:
                model.setTrack(s);
                System.out.println("Tried to load track: " + s);
                break;
            case SET_SPEED:
                timer.stop();
                INTERVAL = Integer.parseInt(s);
                newTimer();
                timer.start();
                break;
        }
    }
        
    public void doCmd(Enumerators o){
        switch (o) {
            case SPEED_DOWN:
                timer.stop();
                INTERVAL *= 1.2;
                newTimer();
                timer.start();
                break;
            case SPEED_UP:
                timer.stop();
                INTERVAL *= 0.8;
                newTimer();
                timer.start();
                break;
            case RESET_GAME:
                model.playStartAudio();
                model.doReset();
                changeDimension();
                view.showPause(false);
                break;
            case SHOW_HIGHSCORES:
                break;
            case ENABLE_AI:
                model.setUseAI(model.isNotUsingAI());
                break;
            case OPTIONS:
                view.displayOptionsMenu();
                model.setPaused(true);
                break;
            case EXIT_GAME:
                System.exit(0);
                break;
            case PAUSE_GAME:
                model.setPaused(true);
                break;
            case START_MENU:
                model.setPaused(true);
                view.displayStartMenu();
                break;
            case START_GAME:
                model.playStartAudio();
                model.doReset();
                changeDimension();
                view.showPause(false);
                view.displayGame();
                break;
        }
    }
}


