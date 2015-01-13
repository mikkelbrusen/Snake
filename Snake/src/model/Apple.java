package model;

/**
 * Generates an apple on a random available field
 * @author BusterK
 */
public class Apple{
    private final int position;
    
    public Apple(Model model){
        this.position = ((int)(Math.random()*100)*model.getAvailableFields().size())/100;
        model.setFieldValue(Objects.APPLE, model.getAvailableFields().get(position));
    }
}