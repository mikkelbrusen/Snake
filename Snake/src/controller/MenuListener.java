package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.View;
import model.Model;

public class MenuListener implements MouseListener{
	private Model model;
	private View view;
	
	
	public MenuListener(View view, Model model){
		super();
		this.model = model;
		this.view = view;
				
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		menuSelector(e);
		
	}

	
	public void mousePressed(MouseEvent e) {
		
		
	}

	
	public void mouseReleased(MouseEvent e) {
		
		
	}

	
	public void mouseEntered(MouseEvent e) {
		
		
	}

	
	public void mouseExited(MouseEvent e) {
		
		
	}
	
	private void menuSelector(MouseEvent e){
		
		int button = e.getButton();
		
		if (button == 1){
			model.doReset();
		}
		
		
		
	}

}
