package model;


/**
 *
 * @author BusterK
 */
public class Field{
    private final int height;
    private final int width;
    private Objects type;
    
    public Field(int width, int height){
        this.width = width;
        this.height = height;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public void setType(Objects type){
        this.type = type;
    }
    
    public Objects getType(){
        return type;
    }
    
}
