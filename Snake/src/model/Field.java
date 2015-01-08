package model;


/**
 *
 * @author BusterK
 */
public class Field{
    private final int height;
    private final int width;
    private Objects type;
    
    public Field(int height, int width){
        this.height = height;
        this.width = width;
    }
    
    public int getWidth(){
        return height;
    }
    
    public int getHeight(){
        return width;
    }
    
    public void setType(Objects type){
        this.type = type;
    }
    
    public Objects getType(){
        return type;
    }
    
}
