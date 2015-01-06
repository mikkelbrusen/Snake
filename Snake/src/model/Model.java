package model;

/**
 * Creates a new game model with a gamefield of size n x m, a snake in the middle, and a random apple.
 * Valid public functions are:
 * getGameField() - Returns a array of type Objecs which is an enumerator.
 * moveSnake(char) - Moves the snake in direction N, E, S or W.
 * 
 * @TODO:
 * New apple is not implemented
 * Empty fields are not in a list yet. Needed for Apple
 * Snake does not check what is in the new position.
 * @author BusterK
 */
public class Model {
    private Objects[][] gameField;
    private Snake snake;
    private Apple apple;
    private Dimension dim;
    private Objects obj;
    
    public Model(int rows, int cols){
        this.dim = new Dimension(rows,cols);
        this.gameField = new Objects[dim.getRows()][dim.getCols()];
        this.snake = new Snake(dim.getRows()/2,dim.getCols()/2,this);
        newApple();
    }
    /*
     * Takes input on Snake.move in form of a char.
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
    
    public Objects[][] getGameField(){
        return this.gameField;
    }
    
    public void setFieldValue(Objects val, int x, int y){
        switch(val){
            case APPLE:
                this.gameField[x][y] = obj.APPLE;
                break;
            case SNAKE:
                this.gameField[x][y] = obj.SNAKE;
                break;
            case WALL:
                this.gameField[x][y] = obj.WALL;
                break;
        }
    }
    
    public int getAppleRow(){
        return apple.getRow();
    }
    
    public int getAppleCol(){
        return apple.getCol();
    }
    
    public void newApple(){
        
    }
}
class Dimension{
    private final int rows;
    private final int cols;
    
    public Dimension(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
    }
    
    public int getRows(){
        return rows;
    }
    
    public int getCols(){
        return cols;
    }
}
