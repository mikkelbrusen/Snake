package model;

/**
 *TODO:
 * Create Dimension
 * @author BusterK
 */
public class Model {
    private char[][] gameField;
    private Snake snake;
    private Apple apple;
    
    public Model(int x, int y){
        this.gameField = new char[x][y];
//        this.snake = new Snake(x/2,y/2,this);
        this.apple = new Apple((int)Math.random()*x,(int)Math.random()*y);
    }
    
    public int getAppleX(){
        return apple.getX();
    }
    
    public int getAppleY(){
        return apple.getY();
    }
    
    public void newApple(){
        
    }
}
class Dimension{
    
}
