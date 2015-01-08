package model;

import java.util.LinkedList;
import java.awt.Dimension;

/**
 * Creates a new game model with a gamefield of size n x m, a snake in the middle, and a random apple.
 * Valid public functions are:
 * getGameField() - Returns a array of type Objecs which is an enumerator.
 * moveSnake(char) - Moves the snake in direction N, E, S or W.
 * 
 * @TODO:
 * Snake can move outside borders
 * New apple is not implemented
 * Available fields are not put in list
 * Only apples are removing available fields
 * Empty fields are not in a list yet. Needed for Apple
 * Snake does not check what is in the new position.
 * @author BusterK
 */
public class Model {
    private Field[][] gameField;
    private Snake snake;
    private Apple apple;
    private Dimension dim;
    private Objects obj;
    
    private LinkedList<Field> availableFields;

    public Model(Dimension dimension){
        this.dim = dimension;
        this.gameField = new Field[dimension.height][dimension.width]();
        this.availableFields = new LinkedList<Field>();
        
        for (int i = 0; i < dimension.height; i++){
            for (int j = 0; j < dimension.width; j++){
                Field field = new Field(i,j);
                field.setType(obj.BLANK);
                availableFields.addFirst(field);
                gameField[i][j] = field;
            }
        }
        this.apple = new Apple(this);
        this.snake = new Snake(dimension.height/2,dimension.width/2,this);
    }
    /**
    * Takes input and moves the snake
    * Legal values are N, S, E, W representing "North", "South", "East", "West"
    */
    public void moveSnake(char dir){
        switch(dir){
            case 'N':
                snake.walk(0, 1);
                break;
            case 'S':
                snake.walk(0, -1);
                break;
            case 'E':
                snake.walk(1, 0);
                break;
            case 'W':
                snake.walk(-1, 0);
                break;
        }        
    }
    
    public Field[][] getGameField(){
        return this.gameField;
    }
    
    protected LinkedList<Field> getAvailableFields(){
        return availableFields;
    }
    
    protected void setFieldValue(Objects val, Field field){
        switch(val){
            case APPLE:
                this.gameField[field.getHeight()][field.getWidth()].setType(obj.APPLE);
                this.availableFields.remove(field);
                break;
            case SNAKE:
                this.gameField[field.getHeight()][field.getWidth()].setType(obj.SNAKE);
                this.availableFields.remove(field);
                break;
            case WALL:
                this.gameField[field.getHeight()][field.getWidth()].setType(obj.WALL);
                this.availableFields.remove(field);
                break;
            case BLANK:
                this.gameField[field.getHeight()][field.getWidth()].setType(obj.BLANK);
                this.availableFields.add(field);
                break;
        }
    }
    
    protected void newApple(Field oldPosition){
        this.availableFields.add(oldPosition);
        this.apple = new Apple(this);
    }
    
    public Dimension getDimension(){
        return this.dim;
    }
}
