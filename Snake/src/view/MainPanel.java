package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

import model.*;

public class MainPanel extends JPanel {
	
	public static final int SCALE = 15;
	
	private Model model;
	private Dimension size;
	
	public MainPanel(Dimension size, Model model) {
		super();
		this.size = size;
		this.setLayout(new GridLayout(size.height*SCALE,size.width*SCALE));
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(size.height*SCALE,size.width*SCALE));
		this.setOpaque(true);
		this.model = model;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Field[][] gameField = model.getGameField();
		
		
		for(int i = 0; i < size.height; i++) {
			for(int j = 0; j < size.width; j++) {
				gameField[i][j].getType();
				if(gameField[i][j].getType() == Objects.APPLE){
					g.setColor(Color.RED);
					g.fillRect(i*SCALE, j*SCALE, SCALE, SCALE);
				}
				
				else if(gameField[i][j].getType() == Objects.SNAKE){
					g.setColor(Color.GREEN);
					g.fillRect(i*SCALE, j*SCALE, SCALE, SCALE);
				}
				
				else {
					g.setColor(Color.WHITE);
					g.fillRect(i*SCALE, j*SCALE, SCALE, SCALE);
				}
				
			}

		}
	}

}
