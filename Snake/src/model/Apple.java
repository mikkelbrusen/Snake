package model;

/**
 * Generates an apple on a random available field
 */
public class Apple{
    public Apple(Model model){
        generateNewAppleFromRandomPosition(model);
    }

    private void generateNewAppleFromRandomPosition(Model model) {
        model.setFieldValue(Enumerators.APPLE, model.getAvailableFields().get(generateRandomPositionFromAvailableFields(model)));
    }

    private int generateRandomPositionFromAvailableFields(Model model){
        return ((int)(Math.random()*100)*model.getAvailableFields().size())/100;
    }
}