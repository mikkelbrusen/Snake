package model;

/**
 *
 * @author BusterK
 */
public class Apple{
    private int row;
    private int col;
    
    public Apple(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }
}