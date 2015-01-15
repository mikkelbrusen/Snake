package controller;

import model.Enumerators;
import model.Model;
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
    	System.out.println(INTERVAL);

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
        }
    }
        
    public void doCmd(Enumerators o){
        switch (o) {
            case RESET_GAME:
                model.playStartAudio();
                model.doReset();
                changeDimension();
                view.showPaused(false);
                break;
            case SHOW_HIGHSCORES:
                break;
            case ENABLE_AI:
                model.setUseAI(model.isNotUsingAI());
                break;
            case SPEED_UP:
                timer.stop();
                this.INTERVAL *= 0.5;
                newTimer();
                timer.start();
                break;
            case SPEED_DOWN:
                timer.stop();
                if (INTERVAL == 0)
                    INTERVAL = 1;
                else
                    this.INTERVAL *= 2;
                newTimer();
                timer.start();
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
                view.showPaused(false);
                view.displayGame();
                break;
            case SET_SPEED:
            	int speed = view.getSpeed();
            	this.INTERVAL = speed;
            	break;
        }
    }
}


