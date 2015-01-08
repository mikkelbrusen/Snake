package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

import model.*;

public class MainPanel extends JPanel {
	
	public static final int SCALE = 10;
	
	private Model model;
	private Dimension size;
	
	public MainPanel(Dimension size) {
		super();
		this.size = size;
		this.setLayout(new GridLayout(size.height,size.width));
		this.setBackground(Color.WHITE);
		this.setPreferredSize(size);
		this.setOpaque(true);
		this.model = new Model(size.width/SCALE, size.height/SCALE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Field[][] gameField = model.getGameField();
		
		
		for(int i = 0; i < size.width/SCALE; i++) {
			for(int j = 0; j < size.height/SCALE; j++) {
				gameField[i][j].getType();
				if(gameField[i][j].getType() == Objects.APPLE){
					g.setColor(Color.RED);
					g.fillRect(i*SCALE, j*SCALE, SCALE, SCALE);
                                        System.out.println("Found Apple!");
				}
				
				else if(gameField[i][j].getType() == Objects.SNAKE){
					g.setColor(Color.GREEN);
					g.fillRect(i*SCALE, j*SCALE, SCALE, SCALE);
                                        System.out.println("Found Snake!");
				}
				
				else {
					g.setColor(Color.WHITE);
					g.fillRect(i*SCALE, j*SCALE, SCALE, SCALE);
				}
				
			}
			
			
		}
	}

}
