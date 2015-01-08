package controller;

import java.awt.Dimension;

/**
 *
 * @author BusterK
 */
public class Main {
    public static void main(String[] args) {
            try{
                int x = Integer.parseInt(args[0]);
                int y = Integer.parseInt(args[1]);
                Controller controller = new Controller(new Dimension(x,y));
            }
            catch(ArrayIndexOutOfBoundsException e){
                Controller controller = new Controller(new Dimension(25,50));
            }
	}
}
