package model;

/**
 *TODO:
 * New apple is not implemented
 * Enumerator needs to be put.
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
        this.apple = new Apple((int)Math.random()*dim.getRows(),(int)Math.random()*dim.getCols());
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
    
    public int getAppleX(){
        return apple.getRow();
    }
    
    public int getAppleY(){
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
