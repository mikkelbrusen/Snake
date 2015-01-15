package view;

import controller.Controller;
import model.Enumerators;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class StartMenu extends JPanel implements ActionListener {

	private final Controller controller;
	
	public StartMenu(Controller controller) {
		
		this.controller = controller;
		
		//Set Layout type to box
		BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(box);
		this.setBorder(new EmptyBorder(400, 50, 200, 50));
		
		//Add title
		JLabel title = new JLabel("Snake");
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setFont(new Font("Comic Sans MS", Font.ITALIC, 72));
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.add(title);
		
		//Add space to next button
		this.add(Box.createRigidArea(new Dimension(0,50)));
		
		//Create start button
		JButton start = new JButton("Start game");
		start.setFont(new Font("Start game", Font.PLAIN, 24));
		start.setAlignmentX(CENTER_ALIGNMENT);
		start.addActionListener(this);
		this.add(start);
		
		//Add space to next button
		this.add(Box.createRigidArea(new Dimension(0,50)));
		
		//Add options button
		JButton options = new JButton("Options");
		options.setFont(new Font("Options", Font.PLAIN, 24));
		options.setAlignmentX(CENTER_ALIGNMENT);
		options.addActionListener(this);
		this.add(options);
		
		//Add space to next button
		this.add(Box.createRigidArea(new Dimension(0,50)));
		
		//Add quit button
		JButton quit = new JButton("Exit game");
		quit.setFont(new Font("Exit game", Font.PLAIN, 24));
		quit.setAlignmentX(CENTER_ALIGNMENT);
		quit.addActionListener(this);
		this.add(quit);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
		case "Start game":
			controller.doCmd(Enumerators.RESET_GAME);
			controller.doCmd(Enumerators.START_GAME);
			break;
		case "Options":
			controller.doCmd(Enumerators.OPTIONS);
			break;
		case "Exit game":
			controller.doCmd(Enumerators.EXIT_GAME);
			break;
		}
		
	}
}
