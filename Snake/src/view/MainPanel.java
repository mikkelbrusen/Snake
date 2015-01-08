package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


import javax.swing.JPanel;

import model.*;

public class MainPanel extends JPanel {
	
	public static final int SCALE = 30;
	
	static BufferedImage IHEAD;
	static BufferedImage IBODY;
	static BufferedImage IAPPLE;
	
	private Model model;
	private Dimension size;
	
	public MainPanel(Dimension size, Model model) {
		super();
		this.size = size;
		this.setLayout(new GridLayout(size.width*SCALE,size.height*SCALE));
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(size.width*SCALE,size.height*SCALE));
		this.setOpaque(true);
		this.model = model;
		
		this.IHEAD = loadImage("NHead.png");
		this.IBODY = loadImage("Body.png");
		this.IAPPLE = loadImage("Apple.png");
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Field[][] gameField = model.getGameField();

		
		Color freeTiles;
		if(model.isGameOver()){
			freeTiles = Color.RED;
		}
		else freeTiles = Color.WHITE;
		
		for(int i = 0; i < size.width; i++) {
			for(int j = 0; j < size.height; j++) {
				gameField[i][j].getType();
				
				g.setColor(freeTiles);
				g.fillRect(i*SCALE, j*SCALE, SCALE, SCALE);
				
				if(gameField[i][j].getType() == Objects.APPLE){
					g.drawImage(IAPPLE, i*SCALE, j*SCALE, SCALE, SCALE, null);
				} else 
				if(gameField[i][j].getType() == Objects.SNAKE) {
					g.drawImage(IHEAD, i*SCALE, j*SCALE, SCALE, SCALE, null);
				}
//				if(gameField[i][j].getType() == Objects.APPLE){
//					g.setColor(Color.RED);
//					g.fillRect(i*SCALE, j*SCALE, SCALE, SCALE);
//					g.drawImage(IAPPLE, i*SCALE, j*SCALE, SCALE, SCALE, null);
//				}
//				
//				else if(gameField[i][j].getType() == Objects.SNAKE){
//					g.setColor(Color.GREEN);
//					g.fillRect(i*SCALE, j*SCALE, SCALE, SCALE);
//				}
//				
//				else {
//					g.setColor(freeTiles);
//					g.fillRect(i*SCALE, j*SCALE, SCALE, SCALE);
//				}	
			}
		}
	}
	
	private BufferedImage loadImage(String s) {
		BufferedImage image;
		try {
			image = ImageIO.read(new File(s));
			return image;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

}
