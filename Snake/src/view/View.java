/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Model;

/**
 *
 * @author BusterK
 */
public class View extends JFrame {
	
	private JPanel snakePanel;
	Model model;
	
	public View(Model model) {
		
		super();
		this.snakePanel = new JPanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	
    
}
