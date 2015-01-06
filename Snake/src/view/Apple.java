package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

public class Apple {
	
	private int x, y;
	private Color red = Color.RED;
	private Dimension size;
	
	public Apple(int x, int y) {
		this.x = x;
		this.y = y;
		size = new Dimension(x,y);
	}
	
	public Color getColor(){
        return red;
    }
    public Dimension getPosition(){
        return size;
    }
}
