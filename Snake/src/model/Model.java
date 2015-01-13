package model;

import AI.AI;
import java.util.LinkedList;
import java.awt.Dimension;
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
    protected static int MAX_WORMHOLES = 10;
    private boolean useAI;
    private Field[][] gameField;
    private Snake snake;
    private Apple apple;
    private Dimension dimension;
    private boolean gameOver;
    private String fileName;
    private int score;
    private int highScore;
    private boolean pause;
    private AI ai;
    private int theme;
    
    private LinkedList<Field> availableFields;
    private Field[] wormHoles;

    public Model(Dimension dimension, String fileName){
        this.useAI = true;
        this.availableFields = new LinkedList<>();
        this.wormHoles = new Field[MAX_WORMHOLES];
        this.fileName = fileName;
        this.dimension = dimension;
        this.theme = 0;

        doReset();
    }
    
    public void setUseAI(boolean b){
        this.useAI = b;
    }
    
    public boolean getUseAI(){
        return this.useAI;
    }

    private boolean loadTrack(String fileName){
        try {
            Scanner sc = new Scanner(new FileReader(fileName));
            int whcount = 0;
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
                        field.setType(Objects.WALL);
                    }
                    else if(Character.isDigit(line.charAt(i))){
                        field.setType(Objects.WORMHOLE);
                        field.setWhNumber(line.charAt(i));
                        wormHoles[whcount] = field;
                        whcount++;
                    }
                    else{
                        field.setType(Objects.BLANK);
                        availableFields.addFirst(field);
                    }
                    gameField[i][lineno] = field;
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
    
    public Field getSnakePosition(){
        return snake.getPosition();
    }
    
    public void setTrack(String fileName){
        this.fileName = fileName;
    }
    
    public boolean isGameOver(){
        return gameOver;
    }
    
    protected void setGameOver(){
        this.gameOver = true;
    }
    
    public boolean isPaused(){
        return pause;
    }
    
    public void setPaused(){
        this.pause = !pause;
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
    public LinkedList<Field> getAvailableFields(){
        return this.availableFields;
    }
    
    public final void doReset(){
        this.gameOver = false;
        this.gameField = new Field[this.dimension.width][dimension.height];
        this.availableFields = new LinkedList<>();
        this.gameOver = false;
        
        if (!(loadTrack(fileName))){
            for (int i = 0; i < dimension.width; i++){
                for (int j = 0; j < dimension.height; j++){
                    Field field = new Field(i,j);
                    field.setType(Objects.BLANK);
                    availableFields.addFirst(field);
                    gameField[i][j] = field;
                }
            }
        }
        this.snake = new Snake(this);
        this.apple = new Apple(this);
        if(this.score > this.highScore)
            this.highScore = this.score;
        this.score = 0;
        this.ai = new AI(this);
    }
    
    public void moveSnake() throws InterruptedException{
        if(useAI){
            snake.setDirection(ai.run());
        }
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
    
    public boolean snakeHasTakenStep(){
        return snake.hasTakenStep();
    }
    
    protected void setFieldValue(Objects val, Field field){
        if(field.getType() != Objects.WORMHOLE){
            switch(val){
                case BLANK:
                    this.gameField[field.getWidth()][field.getHeight()].setType(Objects.BLANK);
                    if (!(this.availableFields.contains(field)))
                        this.availableFields.add(field);
                    break;
                case APPLE:
                    this.gameField[field.getWidth()][field.getHeight()].setType(Objects.APPLE);
                    while(this.availableFields.contains(field))
                        this.availableFields.remove(field);
                    break;
                case SNAKE:
                    this.gameField[field.getWidth()][field.getHeight()].setType(Objects.SNAKE);
                    while(this.availableFields.contains(field))
                        this.availableFields.remove(field);
                    break;
                case WALL:
                    this.gameField[field.getWidth()][field.getHeight()].setType(Objects.WALL);
                    while(this.availableFields.contains(field))
                        this.availableFields.remove(field);
                    break;
                case HEAD:
                    this.gameField[field.getWidth()][field.getHeight()].setType(Objects.HEAD);
                    while(this.availableFields.contains(field))
                        this.availableFields.remove(field);
                    break;
                case TAIL:
                    this.gameField[field.getWidth()][field.getHeight()].setType(Objects.TAIL);
                    while(this.availableFields.contains(field))
                        this.availableFields.remove(field);
                    break;
            }
        }
    }
    
    protected void newApple(){
        this.score += 1;
        this.apple = new Apple(this);
    }
    
    public char getSnakeDirection(){
        return snake.getDirection();
    }
    
    public int getScore(){
        return this.score;
    }
    
    public int getHighScore(){
        return this.highScore;
    }
    
    public void setTheme(int i) {
    	if(i == 0 || i == 1) {
    		this.theme = i;
    	}
    }
    
    public int getTheme(){
    	return this.theme;
    }
    
    public Dimension getDimension(){
        return this.dimension;
    }
    
    protected Field[] getWormHoles(){
        return this.wormHoles;
    }
}
