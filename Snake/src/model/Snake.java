package model;

import java.util.Deque;

/**
 * @author Buster K. Mejborn
 * 
 * TODO:
 * Fix æble dimsen
 * Snake does not check if it has hit a wall or a part of itself.
 */
public class Snake {
    SnakePosition currentPosition;
    Deque queue;
    Model model;
    
    public Snake(int x, int y, Model model){
        this.model = model;
        this.currentPosition = new SnakePosition(x,y);
        
        queue.add(currentPosition);
        model.setFieldValue(Objects.SNAKE, currentPosition.getX(), currentPosition.getY());
        
        SnakePosition newPosition = new SnakePosition(x+1,y);
        queue.add(newPosition);
        model.setFieldValue(Objects.SNAKE,newPosition.getX(),newPosition.getY());
    }
    
    public void walk(int x, int y){
        SnakePosition newPosition;
        if(isReverseDirection(x,y))
            //Do nothing at the moment.
            //Later add function to move the snake in same as last direction.
            currentPosition.Move(0, 0);
        else{
            currentPosition.Move(x, y);
            newPosition = currentPosition;
            queue.add(newPosition);
            if (hasEatenApple(currentPosition)){
                //Dont remove tail from queue
                //Draw the new snake head
            }
            
            else{
                //Remove the tail from the queue, and do checks to see if there's a snake or a wall in the new position.
                 //Remove tail from queue
                //Insert function to check if snake has died.
                model.setFieldValue(Objects.BLANK, currentPosition.getX(), currentPosition.getY());
                queue.removeLast();
            }
        }
    }
    
    private boolean hasEatenApple(SnakePosition position){
        if(model.getAppleX() == position.getX() && model.getAppleY() == position.getY()){
            model.newApple();
            return true;
        }
        else
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

    public SnakePosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    
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
