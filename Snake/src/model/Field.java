package model;

public class Field{
    private final int height;
    private final int width;
    private Enumerators type;
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
    
    protected void setType(Enumerators type){
        this.type = type;
    }
    
    protected int getWhNumber(){
        return this.whNumber;
    }
    
    public Enumerators getType(){
        return type;
    }
    
    protected void setWhNumber(int n){
        this.whNumber = n;
    }
}
