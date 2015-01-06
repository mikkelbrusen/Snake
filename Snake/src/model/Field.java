package model;

/**
 *
 * @author BusterK
 */
public class Field{
    private final int row;
    private final int col;
    private Objects type;
    
    public Field(int x, int y){
        this.row = x;
        this.col = y;
    }
    
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }
    
    public void setType(Objects type){
        this.type = type;
    }
    
    public Objects getType(){
        return type;
    }
    
}
