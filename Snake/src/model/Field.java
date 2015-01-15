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

    int getWhNumber() {
        return this.whNumber;
    }

    void setWhNumber(int n) {
        this.whNumber = n;
    }
    
    public Enumerators getType(){
        return type;
    }

    void setType(Enumerators type) {
        this.type = type;
    }
}
