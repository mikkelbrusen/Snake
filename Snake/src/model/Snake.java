package model;

import java.util.LinkedList;

/**
 * @author Buster K. Mejborn
 * 
 * TODO:
 */
public class Snake {
    private Field position;
    private Field oldPosition;
    private LinkedList<Field> queue;
    private Model model;
    private char reverseDirection;
    private char direction;
    private boolean isReverseDirection;
    private boolean hasTakenStep;
    
    protected Snake(Model model){
        int width = model.getDimension().width/2;
        int height = model.getDimension().height/2;
        
        this.model = model;
        this.queue = new LinkedList<Field>();
        //Sets the snake at length 2 to go 1 
        Field field = model.getGameField()[width][height];
        queue.add(field);
        model.setFieldValue(Objects.TAIL, field);
        
        field = model.getGameField()[width-1][height];
        queue.add(field);
        model.setFieldValue(Objects.HEAD, field);
        position = field;
        this.direction = 'W';
        this.reverseDirection = 'E';
        this.isReverseDirection = false;
        this.hasTakenStep = true;
    }
    protected void setDirection(char direction){
        if (this.reverseDirection == direction){
            this.isReverseDirection = true;
        }
        else if (this.hasTakenStep){
            this.isReverseDirection = false;
            this.hasTakenStep = false;
            
            switch(direction){
            case 'N':
                this.reverseDirection = 'S';
                this.direction = 'N';
                break;
            case 'S':
                this.reverseDirection = 'N';
                this.direction = 'S';
                break;
            case 'E':
                this.reverseDirection = 'W';
                this.direction = 'E';
                break;
            case 'W':
                this.reverseDirection = 'E';
                this.direction = 'W';
                break;
            }
        }
        else{
            
        }
    }
    protected boolean hasHitEdge(Field positon){
        if(position.getHeight() == 0 && this.reverseDirection == 'S'){
            this.position = model.getGameField()[position.getWidth()][model.getDimension().height-1];
            return true;
        }
        if(position.getHeight() == model.getDimension().getHeight()-1 && this.reverseDirection == 'N'){
            this.position = model.getGameField()[position.getWidth()][0];
            return true;
        }
        if(position.getWidth() == 0 && this.reverseDirection == 'E'){
            this.position = model.getGameField()[model.getDimension().width-1][position.getHeight()];
            return true;
        }
        if(position.getWidth() == model.getDimension().getWidth()-1 && this.reverseDirection == 'W'){
            this.position = model.getGameField()[0][position.getHeight()];
            return true;
        }
        else
            return false;
    }
    
    protected char getReverseDirection(){
        return reverseDirection;
    }
    
    protected char getDirection(){
        return direction;
    }
    
    protected void walk(int widht, int height){
        if(isReverseDirection){
            //Do nothing at the moment.
            //Later add function to move the snake in same as last direction.
            }
        else{
            this.hasTakenStep = true;
            this.oldPosition = position;
            //Check if next position is at the other edge of the screen.
            if(!hasHitEdge(position)){
                position = model.getGameField()[position.getWidth()+widht][position.getHeight()+height];
            }
            
            queue.add(position);
            //Before marking the field as a snake, check if there's and apple, snake or wall there.
            
            switch(position.getType()){
                case APPLE:
                    model.newApple(position);
                    model.setFieldValue(Objects.HEAD, position);
                    model.setFieldValue(Objects.SNAKE, oldPosition);
                    model.setFieldValue(Objects.TAIL, queue.getFirst());
                    break;
                case WALL:
                    model.setGameOver();
                    break;
                case SNAKE:
                    model.setGameOver();
                    break;
                default:
                    model.setFieldValue(Objects.BLANK, queue.getFirst());
                    model.setFieldValue(Objects.HEAD, position);
                    model.setFieldValue(Objects.SNAKE, oldPosition);
                    queue.removeFirst();
                    model.setFieldValue(Objects.TAIL, queue.getFirst());
                    break;  
            }
        }
    }
}

