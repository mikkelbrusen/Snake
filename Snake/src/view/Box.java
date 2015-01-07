package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;

public class Box extends JLabel {


	private Color red = Color.RED;
	private Dimension size;
	
	public Box(Dimension size) {
		this.size = size;
		this.setBackground(red);
		this.setOpaque(true);
		this.setLocation(100, 100);
	}
	
	public Color getColor(){
        return red;
    }
    public Dimension getPosition(){
        return size;
    }
    
    public void drawBox(){
    	repaint();
    }
    
   
}
