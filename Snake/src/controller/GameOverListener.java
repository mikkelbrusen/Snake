package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Model;
import view.View;

public class GameOverListener implements ActionListener{
		
	private Model model;
	private View view;
	
	public GameOverListener(Model model, View view) {
		super();
		
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		
	}
}
