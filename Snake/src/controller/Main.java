package controller;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;

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
            fileName = "";
        }
            
        
        try{
            int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);
            Controller controller = new Controller(new Dimension(x,y),fileName);
        }
        catch(ArrayIndexOutOfBoundsException e){
            Controller controller = new Controller(new Dimension(50,25),fileName);
        }
        catch(NumberFormatException e){
            Controller controller = new Controller(new Dimension(50,25),fileName);
        }
    }
}
