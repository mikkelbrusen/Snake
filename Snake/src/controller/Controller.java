package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Timer;

import model.Model;
import view.*;

public class Controller {
        private final static int INTERVAL = 50;
	private Model model;
	private View view;
    private Dimension dimension;
	
	public Controller(Dimension dimension,File track) {
            this.dimension = dimension;
            this.model = new Model(dimension,track);
            View view = new View(model);
            this.view = view;
            Timer timer = new Timer(INTERVAL,new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(model.isGameOver()){
                           // timer.stop();
                        }
                        else
                            model.moveSnake();
                            view.repaint();
                    }
            });
                
            timer.start();

            DirectionListener d = new DirectionListener(model,view,this);
            view.addKeyListener(d);
	}
        
        protected void newModel(){
            model.doReset();
        }
}
