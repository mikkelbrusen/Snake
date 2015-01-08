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
                Controller controller = new Controller(new Dimension(x,y),null);
            }
            catch(ArrayIndexOutOfBoundsException e){
                Controller controller = new Controller(new Dimension(50,25),null);
            }
            catch(NumberFormatException e){
                Controller controller = new Controller(new Dimension(50,25),null);
            }
	}
}
