package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Model;
import view.View;

public class DirectionListener implements KeyListener {
	
	private Model model;
	private View view;
        private Controller controller;
        
	public DirectionListener(Model model, View view, Controller controller) {
		super();
		
                this.controller = controller;
		this.model = model;
		this.view = view;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		forceDirection(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	private void forceDirection(KeyEvent e) {
		/*
		 * UP: 38 or 87
		 * DOWN: 40 or 83
		 * LEFT: 37 or 65
		 * RIGHT: 39 or 68
		 */
		
		int keyCode = e.getKeyCode();
		
		if( keyCode==38 || keyCode==87) {
			model.changeSnakeDirection('N');
		} else if ( keyCode == 40 || keyCode == 83) {
			model.changeSnakeDirection('S');
		} else if ( keyCode == 37 || keyCode == 65) {
			model.changeSnakeDirection('W');
		} else if ( keyCode == 39 || keyCode == 68) {
			model.changeSnakeDirection('E');
		}
		
        view.repaint();
        
        if (model.isGameOver() == true) {
            view.doAnnounce();
            controller.newModel();
        }
        
	}
	
	

}
