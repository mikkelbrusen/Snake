package model;


/**
 *
 * @author BusterK
 */
public class Field{
    private final int height;
    private final int width;
    private Objects type;
    private int whNumber;
    
    public Field(int width, int height){
        this.width = width;
        this.height = height;
        this.whNumber = 0;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    protected void setType(Objects type){
        this.type = type;
    }
    
    protected int getWhNumber(){
        return this.whNumber;
    }
    
    public Objects getType(){
        return type;
    }
    
    protected void setWhNumber(int n){
        this.whNumber = n;
    }
}
