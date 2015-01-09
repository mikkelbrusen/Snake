package model;

import java.util.LinkedList;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Creates a new game model with a game field of size n x m, a snake in the middle, and a random apple.
 * Valid public functions are:
 * getGameField() - Returns a array of type Objecs which is an enumerator.
 * moveSnake(char) - Moves the snake in direction N, E, S or W.
 * 
 * @TODO:
 * @author BusterK
 */
public class Model {
    private Field[][] gameField;
    private Snake snake;
    private Apple apple;
    private Dimension dimension;
    private Objects obj;
    private boolean gameOver;
    private String fileName;
    private int score;
    
    private LinkedList<Field> availableFields;

    public Model(Dimension dimension, String fileName){
        this.availableFields = new LinkedList<Field>();
        this.fileName = fileName;
        this.dimension = dimension;

        doReset();
    }
    public boolean loadTrack(String fileName){
        try {
            Scanner sc = new Scanner(new FileReader(fileName));
            int width = sc.nextInt();
            int height = sc.nextInt();
            this.dimension = new Dimension(width,height);
            this.gameField = new Field[dimension.width][dimension.height];
            
            sc.nextLine();
            
            int lineno = 0;
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                for(int i = 0; i < width; i++){
                    Field field = new Field(i,lineno);
                    if(line.charAt(i) == '#'){
                        field.setType(obj.WALL);
                        gameField[i][lineno] = field;
                    }
                    else{
                        field.setType(obj.BLANK);
                        availableFields.addFirst(field);
                        gameField[i][lineno] = field;
                    }
                }
                lineno++;
            }
            sc.close();
            return true;
        } 
        catch (FileNotFoundException e) {
            return false;
        }
    }
    
    /**
    * Takes input and moves the snake
    * Legal values are N, S, E, W representing "North", "South", "East", "West"
    */
    public void setTrack(String fileName){
        this.fileName = fileName;
    }
    
    public boolean isGameOver(){
        return gameOver;
    }
    
    protected void setGameOver(){
        this.gameOver = true;
    }
    public void changeSnakeDirection(char dir){
        switch(dir){
            case 'N':
                snake.setDirection('N');
                break;
            case 'S':
                snake.setDirection('S');
                break;
            case 'E':
                snake.setDirection('E');
                break;
            case 'W':
                snake.setDirection('W');
                break;
        }        
    }
    
    public void doReset(){
        System.out.println("You died! Your score was: " + this.score);
        this.gameOver = false;
        if (!(loadTrack(fileName))){
            this.gameField = new Field[this.dimension.width][dimension.height];
            this.availableFields = new LinkedList<Field>();
            this.gameOver = false;

            for (int i = 0; i < dimension.width; i++){
                for (int j = 0; j < dimension.height; j++){
                    Field field = new Field(i,j);
                    field.setType(obj.BLANK);
                    availableFields.addFirst(field);
                    gameField[i][j] = field;
                }
            }
        }
        this.snake = new Snake(this);
        this.apple = new Apple(this);
        this.score = 0;
    }
    
    public void moveSnake(){
        switch(snake.getDirection()){
            case 'N':
                snake.setDirection('N');
                snake.walk(0, -1);
                break;
            case 'S':
                snake.setDirection('S');
                snake.walk(0, 1);
                break;
            case 'E':
                snake.setDirection('E');
                snake.walk(1, 0);
                break;
            case 'W':
                snake.setDirection('W');
                snake.walk(-1, 0);
                break;
        }
    }
    
    public Field[][] getGameField(){
        return this.gameField;
    }
    
    protected LinkedList<Field> getAvailableFields(){
        return availableFields;
    }
    
    protected void setFieldValue(Objects val, Field field){
        switch(val){
            case APPLE:
                this.gameField[field.getWidth()][field.getHeight()].setType(obj.APPLE);
                this.availableFields.remove(field);
                break;
            case SNAKE:
                this.gameField[field.getWidth()][field.getHeight()].setType(obj.SNAKE);
                this.availableFields.remove(field);
                break;
            case WALL:
                this.gameField[field.getWidth()][field.getHeight()].setType(obj.WALL);
                this.availableFields.remove(field);
                break;
            case BLANK:
                this.gameField[field.getWidth()][field.getHeight()].setType(obj.BLANK);
                this.availableFields.add(field);
                break;
            case HEAD:
                this.gameField[field.getWidth()][field.getHeight()].setType(obj.HEAD);
                break;
            case TAIL:
                this.gameField[field.getWidth()][field.getHeight()].setType(obj.TAIL);
                break;
        }
    }
    
    protected void newApple(Field oldPosition){
        this.score += 1;
        this.availableFields.add(oldPosition);
        this.apple = new Apple(this);
    }
    
    public char getSnakeDirection(){
        return snake.getDirection();
    }
    
    public int getScore(){
        return this.score;
    }
    
    public Dimension getDimension(){
        return this.dimension;
    }
}
