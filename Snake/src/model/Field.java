package model;

public class Field{
    private final int height;
    private final int width;
    private Enumerators type;
    private int whNumber;
    
    // Constructor
    
    public Field(int width, int height){
        this.width = width;
        this.height = height;
        this.whNumber = 0;
    }
    
    // Getters
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }

    int getWhNumber() {
        return this.whNumber;
    }
    
    public Enumerators getType(){
        return type;
    }
    
    // Setters 
    
    void setWhNumber(int n) {
        this.whNumber = n;
    }

    void setType(Enumerators type) {
        this.type = type;
    }
}
