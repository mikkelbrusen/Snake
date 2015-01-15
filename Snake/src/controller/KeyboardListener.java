package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Enumerators;
import model.Model;
import view.View;

import java.util.*;
public class KeyboardListener implements KeyListener {
	
    private final Model model;
    private final View view;
    private final Controller controller;
    
    private List<Integer> Directions;
    private List<Integer> Shortcuts;
        
	public KeyboardListener(Model model, View view, Controller controller) {
		super();
		// Insert the needed keyCodes so that we can easy check if we are to respond to a keystroke or not.
		this.Directions = Arrays.asList(38,87,40,83,37,65,39,68); 
		this.Shortcuts = Arrays.asList(49,50,51,52,53,78,80,79,72,KeyEvent.VK_ESCAPE);		
		
		this.model = model;
		this.view = view;
		this.controller = controller;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Check if the pressed key is a direction key or a shortcut key, and call the respective method,
		
		// IMPORTANT!! - Need to check if game is paused to prevent pause+change direction abuse
		if (Directions.contains(e.getKeyCode())&&!model.isPaused()){
		forceDirection(e);
		}
		else if (Shortcuts.contains(e.getKeyCode())){
		shortCuts(e);
		}
	}

	// The following two overrides are just formalizes and does nothing.
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	/** Change direction by calling the model.changeSnakeDirection with an argument,
	 * based on the KeyEven e */
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

	}
	/** Call different shortcuts implemented in the model, depending on the KeyEvent e */
	private void shortCuts(KeyEvent e){
		/*
		 * N: 78
		 * P: 80
		 * O: 79
		 * H: 72
		 * 1: 49
		 * 2: 50
		 * 3: 51
		 * 4: 52
		 * 5: 53
		 * ESC: KeyEvent.VK_ESCAPE
		 */
		
		int keyCode = e.getKeyCode();
		
		if (keyCode == 78){
			controller.doCmd(Enumerators.RESET_GAME);
			controller.doCmd(Enumerators.START_GAME);
		}
		if (keyCode == 80){
			model.setPaused();
			view.showPaused(model.isPaused());
		}
		if (keyCode == 79){
			controller.doCmd(Enumerators.OPTIONS);
		}
		if (keyCode == 72){
			controller.doCmd(Enumerators.SHOW_HIGHSCORES);
		}
		if (keyCode == KeyEvent.VK_ESCAPE) {
			controller.doCmd(Enumerators.START_MENU);
		}
		if (keyCode == 49){
			model.setTheme(1);
		}
		if ( keyCode == 50){
			model.setTheme(2);
		}
		if ( keyCode == 51){
			controller.doCmd(Enumerators.ENABLE_AI);
		}
		if ( keyCode == 52){
			controller.doCmd(Enumerators.SPEED_UP);
		}
		if (keyCode == 53){
			controller.doCmd(Enumerators.SPEED_DOWN);
		}
	}
	
	
	
	

}
