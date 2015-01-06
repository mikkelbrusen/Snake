package model;

/**
 *
 * @author BusterK
 */
public class Apple{
    private final int row;
    private final int col;
    private final Model model;
    private final int position;
    
    public Apple(Model model){
        this.model = model;
        this.position = ((int)(Math.random()*100)*model.getAvailableFields().size())/100;
        row = model.getAvailableFields().get(position).getRow();
        col = model.getAvailableFields().get(position).getCol(); 
        
        model.getAvailableFields().remove(position);
    }
    
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }
}