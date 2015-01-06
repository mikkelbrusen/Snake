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
		// Model model = new Model();
		View view = new View();
		//Controller controller = new Controller(model, view);
	}

}
