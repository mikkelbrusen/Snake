package model;

/**
 *
 * @author BusterK
 */
public class Apple{
    private final int height;
    private final int width;
    private final Model model;
    private final int position;
    
    public Apple(Model model){
        this.model = model;
        this.position = ((int)(Math.random()*100)*model.getAvailableFields().size())/100;
        height = model.getAvailableFields().get(position).getWidth();
        width = model.getAvailableFields().get(position).getHeight(); 
        
        model.setFieldValue(Objects.APPLE, model.getGameField()[height][width]);
    }
    
    public int getRow(){
        return height;
    }
    
    public int getCol(){
        return width;
    }
}