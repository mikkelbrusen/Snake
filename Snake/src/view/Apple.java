package view;

import java.awt.Color;
import java.awt.Point;

public class Apple {
	
	private Point p;
	private Color red = Color.RED;
	
	public Apple(Point p) {
		this.p = p;
	}
	
	public Color getColor(){
        return red;
    }
    public Point getPosition(){
        return p;
    }
    public int getSize(){
        return 1;
    }
}
