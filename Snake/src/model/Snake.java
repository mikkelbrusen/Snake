package model;

import java.util.LinkedList;

/**
 * @author Buster K. Mejborn
 * 
 * TODO:
 * Snake does nothing if the directions is reverse
 * Snake does not know when the edge has been reached yet
 * Snake does not check if it has hit a wall or a part of itself.
 */
public class Snake {
    Field currentPosition;
    LinkedList<Field> queue;
    Model model;
    char reverseDirection;
    boolean isReverseDirection;
    
    public Snake(int width, int height, Model model){
        this.model = model;
        this.queue = new LinkedList<Field>();
        //Sets the snake at length 2 to go 1 
        Field field = model.getGameField()[width][height];
        queue.add(field);
        model.setFieldValue(Objects.SNAKE, field);
        
        field = model.getGameField()[width+1][height];
        queue.add(field);
        model.setFieldValue(Objects.SNAKE, field);
        currentPosition = field;
        this.reverseDirection = 'E';
        this.isReverseDirection = false;
    }
    public void setDirection(char direction){
        if (this.reverseDirection == direction){
            this.isReverseDirection = true;
        }
        else{
            this.isReverseDirection = false;
            
            switch(direction){
            case 'N':
                this.reverseDirection = 'S';
                break;
            case 'S':
                this.reverseDirection = 'N';
                break;
            case 'E':
                this.reverseDirection = 'W';
                break;
            case 'W':
                this.reverseDirection = 'E';
                break;
            }
        }      
    }
    
    public void walk(int widht, int height){
        if(isReverseDirection){
            //Do nothing at the moment.
            //Later add function to move the snake in same as last direction.
            }
        else{
            //Check if next position is at the other edge of the screen.
            currentPosition = model.getGameField()[currentPosition.getHeight()+widht][currentPosition.getWidth()+height];
            queue.add(currentPosition);
            
            //Before marking the field as a snake, check if there's and apple, snake or wall there.
            model.setFieldValue(Objects.BLANK, queue.getFirst());
            
            switch(currentPosition.getType()){
                case APPLE:
                    model.newApple(currentPosition);
                    model.setFieldValue(Objects.SNAKE, currentPosition);
                    break;
                case WALL:
                    
                    break;
                case SNAKE:
                    
                    break;
                default:
                    model.setFieldValue(Objects.SNAKE, currentPosition);
                    queue.removeFirst();
                    break;  
            }
        }
    }
}

