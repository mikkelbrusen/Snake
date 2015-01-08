package controller;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import model.Model;
import view.*;

public class Controller {
	private Model model;
	private View view;
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		
		DirectionListener d = new DirectionListener(model,view);
		view.addKeyListener(d);
	}
	
	public static void main(String[] args) {
		Model model = new Model(new Dimension(50,50));
		View view = new View(model);
		Controller controller = new Controller(model, view);
	}

}
