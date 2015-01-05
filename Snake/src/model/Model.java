package model;

/**
 *
 * @author BusterK
 */
public class Model {
    private char[][] field;
    private Snake snake;
    private Apple apple;
    
    public Model(int x, int y){
        this.field = new char[x][y];
        this.snake = new Snake(x/2,y/2,this);
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

class Apple{
    private int x;
    private int y;
    
    public Apple(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
}
