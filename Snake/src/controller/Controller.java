package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import model.Model;
import view.*;

public class Controller {
        private final static int INTERVAL = 50;
	private Model model;
	private View view;
	
	public Controller(Model model, View view) {
            this.model = model;
            this.view = view;
            Timer timer = new Timer(INTERVAL,new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(model.isGameOver()){
                            //timer.stop();
                        }
                        else
                            model.moveSnake();
                            view.repaint();
                    }
            });
                
            timer.start();

            DirectionListener d = new DirectionListener(model,view);
            view.addKeyListener(d);
	}
        
        
        
	public static void main(String[] args) {
		Model model = new Model(new Dimension(50,50));
		View view = new View(model);
		Controller controller = new Controller(model, view);
	}

}
