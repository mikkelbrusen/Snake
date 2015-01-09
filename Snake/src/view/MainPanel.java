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
	
	public static final int res[] = new int[] {800,600};
	public static int SCALE;
	
	static BufferedImage IWALL,IHEAD,IBODY,IAPPLE,IOBAMA,ITBODY,ITN,ITS,ITE,ITW,ISAND,IBOMB;
	
	private final Model model;
	private final Dimension size;
	
	public MainPanel(Dimension size, Model model) {
		super();
		this.size = size;
		this.SCALE = res[0]/size.width;
		this.setLayout(new GridLayout(size.width*SCALE,size.height*SCALE));
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(size.width*SCALE,size.height*SCALE));
		this.setOpaque(true);
		this.model = model;
		
		this.IWALL = loadImage("Brick.png");
		this.IHEAD = loadImage("NHead.png");
		this.IBODY = loadImage("Body.png");
		this.IAPPLE = loadImage("Apple.png");
		this.IOBAMA = loadImage("Obama.png");
		this.ITBODY = loadImage("BodyTerrorist.png");
		this.ITN = loadImage("NLeadTerrorist.png");
		this.ITS = loadImage("SLeadTerrorist.png");
		this.ITE = loadImage("ELeadTerrorist.png");
		this.ITW = loadImage("WLeadTerrorist.png");
		this.IBOMB = loadImage("Bomb.png");
		this.ISAND = loadImage("Sand.png");
		
		
		
		
	}
	
	public Dimension getSize(){
		return this.size;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Field[][] gameField = model.getGameField();
		
		// paint fields
		for(int i = 0; i < size.width; i++) {
			for(int j = 0; j < size.height; j++) {

				//draw sand
				g.drawImage(ISAND, i*SCALE, j*SCALE, SCALE, SCALE, null);
				
				// draw apple
				if(gameField[i][j].getType() == Objects.APPLE){
					g.drawImage(IBOMB, i*SCALE, j*SCALE, SCALE, SCALE, null);
				} 
				// draw wall
				else if(gameField[i][j].getType() == Objects.WALL){
					g.drawImage(IWALL, i*SCALE, j*SCALE, SCALE, SCALE, null);
				}
				// draw snake body
				else if(gameField[i][j].getType() == Objects.SNAKE) {		                	
					g.drawImage(ITBODY, i*SCALE, j*SCALE, SCALE, SCALE, null);
				// draw head
                } else if(gameField[i][j].getType() == Objects.HEAD){
                	switch(model.getSnakeDirection()){
		                case 'N':
		                	g.drawImage(ITN, i*SCALE, j*SCALE, SCALE, SCALE, null);
		                    break;
		                case 'S':
		                	g.drawImage(ITS, i*SCALE, j*SCALE, SCALE, SCALE, null);
		                    break;
		                case 'E':
		                	g.drawImage(ITE, i*SCALE, j*SCALE, SCALE, SCALE, null);
		                    break;
		                case 'W':
		                	g.drawImage(ITW, i*SCALE, j*SCALE, SCALE, SCALE, null);
		                    break;
					}
                // draw tail
                } else if(gameField[i][j].getType() == Objects.TAIL){
                	g.drawImage(IOBAMA, i*SCALE, j*SCALE, SCALE, SCALE, null);
                } 
			}
		} // end of painting fields
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
