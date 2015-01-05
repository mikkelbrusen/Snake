package model;

import java.util.Deque;
import javafx.scene.Node;

/**
 * Takes input on Snake.move in form of a char.
 * Legal values are N, S, E, W representing "North", "South", "East", "West"
 * @author Buster K. Mejborn 
 */
public class Snake {
    SnakePosition currentPosition;
    Deque queue;
    
    public void move(char dir){
        switch(dir){
            case 'N':
                walk(0, 1);
                break;
            case 'S':
                walk(0, -1);
                break;
            case 'E':
                walk(1, 0);
                break;
            case 'W':
                walk(-1, 0);
                break;
        }        
    }
    
    private void walk(int x, int y){
        SnakePosition newPosition;
        if(isReverseDirection(x,y))
            //Do nothing at the moment.
            //Later add function to move the snake in same as last direction.
            currentPosition.Move(0, 0);
        else{
            currentPosition.Move(x, y);
            newPosition = currentPosition;
            queue.add(newPosition);
            if (hasEatenApple(currentPosition))
              ;  //Dont remove tail from queue
            else
                queue.remove();//Remove tail from queue   
        }
    }
    
    private boolean hasEatenApple(SnakePosition position){
        //TODO:
        //Make calls to apple / other model control, find out if there's an apple.
        //If there's an apple, remove it. and return true
        //If not, return false
        return false;
    }
    
    private boolean isReverseDirection(int x, int y){
        if ( x == currentPosition.getX()-1 || 
                y == currentPosition.getY()-1 )
            return true;
        else
            return false;
    }
}

class SnakePosition {   
    private int x;
    private int y;

    public int getX() {
            return x;
    }

    public int getY() {
            return y;
    }
    
    public void Move(int x, int y){
        this.x += x;
        this.y += y;
    }
}
