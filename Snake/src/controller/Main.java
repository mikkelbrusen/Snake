package controller;

import java.awt.Dimension;

/**
 *
 * @author BusterK
 */
public class Main {
    public static void main(String[] args) {
        String fileName;
        
        try{
            fileName = args[2];
        }
        catch(ArrayIndexOutOfBoundsException e){
            fileName = "50x25_withWalls";
        }
            
        try{
            int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);
            Controller controller = new Controller(new Dimension(x,y),fileName);
        }
        catch(ArrayIndexOutOfBoundsException | NumberFormatException e){
            Controller controller = new Controller(new Dimension(100,80),fileName);
        }
    }
}
