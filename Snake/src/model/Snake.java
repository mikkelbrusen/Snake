package model;

import java.util.Deque;

/**
 * @author Buster K. Mejborn
 * 
 * TODO:
 * Snake does nothing if the directions is reverse
 * Snake does not know when the edge has been reached yet
 * Has eaten apple does nothing
 * Snake does not check if it has hit a wall or a part of itself.
 */
public class Snake {
    Field currentPosition;
    Deque<Field> queue;
    Model model;
    
    public Snake(int row, int col, Model model){
        this.model = model;
        //Sets the snake at length 2 to go 1 
        Field field = model.getGameField()[row][col];
        queue.add(field);
        model.setFieldValue(Objects.SNAKE, field);
        
        field = model.getGameField()[row+1][col];
        queue.add(field);
        model.setFieldValue(Objects.SNAKE, field);
        currentPosition = field;
    }
    
    public void walk(int row, int col){
        if(isReverseDirection(row,col)){
            //Do nothing at the moment.
            //Later add function to move the snake in same as last direction.
            }
        else{
            //Check if next position is at the other edge of the screen.
            
            currentPosition = model.getGameField()[currentPosition.getRow()+row][currentPosition.getCol()+col];
            queue.add(currentPosition);
            
            //Before marking the field as a snake, check if there's and apple, snake or wall there.
            if (hasEatenApple(currentPosition)){
                //Dont remove tail from queue
                //Draw the new snake head
            }
            else if (hasHitWall(currentPosition)){
                //Implement Death-by-wall
            }
            
            else if (hasHitSnake(currentPosition)){
                //Implement Death-by-snake
            }
            else {
                //Current position is OK. Mark it as snake, and continue
                model.setFieldValue(Objects.SNAKE, currentPosition);
            }
        }
    }
    
    private boolean hasEatenApple(Field position){
        if(position.getType() == Objects.APPLE){
            model.newApple();
            return true;
        }
        else
            return false;
    }
    
    private boolean hasHitWall(Field position){
    if(position.getType() == Objects.WALL)
        return true;
    else
        return false;
    }
    
    private boolean hasHitSnake(Field position){
        model.setFieldValue(Objects.BLANK, queue.getLast());
        
        if(position.getType() == Objects.SNAKE)
            return true;
        else{
            queue.removeLast();
            return false;
        }        
    }
    
    private boolean isReverseDirection(int x, int y){
        if ( x == currentPosition.getCol()-1 || 
                y == currentPosition.getRow()-1 )
            return true;
        else
            return false;
    }
}

