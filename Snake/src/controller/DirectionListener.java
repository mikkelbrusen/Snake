package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Model;
import view.View;

public class DirectionListener implements KeyListener {
	
	private Model model;
	private View view;
	
	public DirectionListener(Model model, View view) {
		super();
		
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
			model.moveSnake('N');
		} else if ( keyCode == 40 || keyCode == 83) {
			model.moveSnake('S');
		} else if ( keyCode == 37 || keyCode == 65) {
			model.moveSnake('W');
		} else if ( keyCode == 39 || keyCode == 68) {
			model.moveSnake('E');
		}
                view.repaint();
		
	}

}
