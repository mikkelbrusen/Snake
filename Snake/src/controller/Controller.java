package controller;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import model.Model;
import view.View;

public class Controller {
	private Model model;
	private View view;
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	
	public static void main(String[] args) {
		Model model = new Model(100,100);
		View view = new View(model);
		Controller controller = new Controller(model, view);
	}

}
