package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Model;
import view.View;

import java.util.*;
public class KeyboardListener implements KeyListener {
	
	private Model model;
	private View view;
    private Controller controller;
    private List<Integer> Directions;
    private List<Integer> Shortcuts;
        
	public KeyboardListener(Model model, View view, Controller controller) {
		super();
		this.Directions = Arrays.asList(38,87,40,83,37,65,39,68); 
		this.Shortcuts = Arrays.asList(78,80,79,72,77);		
        this.controller = controller;
		this.model = model;
		this.view = view;
	}
	
	public KeyboardListener(Model model, View view){
		this.model = model;
		this.view = view;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (Directions.contains(e.getKeyCode())){
		forceDirection(e);
		}
		if (Shortcuts.contains(e.getKeyCode())){
		shortCuts(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println(e);
	}

	private void forceDirection(KeyEvent e) {
		/*
		 * UP: 38 or 87
		 * DOWN: 40 or 83
		 * LEFT: 37 or 65
		 * RIGHT: 39 or 68
		 * N: 78
		 * P: 80
		 * O: 79
		 * H: 72
		 * M: 77 
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
        
        if (model.isGameOver()) {
            view.doAnnounce();
            model.doReset();
        }
        
	}
	
	private void shortCuts(KeyEvent e){
		/*
		 * N: 78
		 * P: 80
		 * O: 79
		 * H: 72
		 * M: 77 
		 */
		
		int keyCode = e.getKeyCode();
		
		if (keyCode == 78){
			model.doReset();
		}
		if (keyCode == 80){
//			pause the game
		}
		if (keyCode == 79){
//			enter options menu
		}
		if (keyCode == 72){
//			show highscores
		}
		if (keyCode == 77){
//			enter menu
		}
		
		
	}
	
	
	
	

}
