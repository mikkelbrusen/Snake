package model;

import AI.AI;
import java.util.LinkedList;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.NoSuchElementException;
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
    //###################################################
    //#                                                 #
    //#          Variables and constructor              #
    //#                                                 #
    //###################################################
    protected static int MAX_WORMHOLES = 10;
    private static int MAX_HIGHSCORES = 2;
    private boolean useAI, hasUsedAI, pause, gameOver;
    private int score, theme;
    private String fileName;
    
    private AI ai;
    private Snake snake;
    private Apple apple;
    private Dimension dimension;
    
    private final LinkedList<HighScore> highScores;
    private LinkedList<Field> availableFields;
    
    private final Field[] wormHoles;
    private Field[][] gameField;
    
    public Model(Dimension dimension, String fileName){
        this.useAI = false;
        this.availableFields = new LinkedList<>();
        this.highScores = new LinkedList<>();
        this.wormHoles = new Field[MAX_WORMHOLES*2];
        this.fileName = fileName;
        this.dimension = dimension;
        this.theme = 0;
        for(int i = 0; i < MAX_HIGHSCORES; i++){
            setNewHighScore("AI");
        }

        doReset();
    }
    
    //###################################################
    //#                                                 #
    //#                  Setters                        #
    //#                                                 #
    //###################################################
    public void setTrack(String fileName){
        this.fileName = fileName;
    }
    public void setPaused(){
        this.pause = !pause;
    }
    public void setUseAI(boolean b){
        this.hasUsedAI = true;
        this.useAI = b;
    }
    public void setNewHighScore(String name){
        if(!(hasUsedAI)){
            this.highScores.add(new HighScore(this.score, name));
            Collections.sort(highScores);
            if(highScores.size() > MAX_HIGHSCORES){
                highScores.removeLast();
            }
        }
    }
    public void setTheme(int i) {
    	if(i == 0 || i == 1) {
    		this.theme = i;
    	}
    }
    public boolean setSnakeHasTakenStep(){
        return snake.hasTakenStep();
    }
    
    //###################################################
    //#                                                 #
    //#          Game field generation                  #
    //#                                                 #
    //###################################################
    public final void doReset(){
        this.gameOver = false;
        this.gameField = new Field[this.dimension.width][dimension.height];
        this.availableFields = new LinkedList<>();
        this.gameOver = false;
        this.hasUsedAI = useAI;
        
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
        this.score = 0;
        this.ai = new AI(this);
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
    
    //###################################################
    //#                                                 #
    //#         Moving the snake                        #
    //#                                                 #
    //###################################################
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
    
    
    
    
    public boolean getUseAI(){
        return this.useAI;
    }
    
    public boolean getHasUsedAI(){
        return this.hasUsedAI;
    }
    
    public int getScore(){
        return this.score;
    }
    
    public Field getSnakePosition(){
        return snake.getPosition();
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
        
    public LinkedList<Field> getAvailableFields(){
        return this.availableFields;
    }
    
    public int getLowestHighScore(){
        try{
            return this.highScores.getLast().getScore();
        }catch(NoSuchElementException e){
            return 0;
        }
    }
    
    public Field[][] getGameField(){
        return this.gameField;
    }
    
    
    
    
    
    protected void newApple(){
        this.score += 1;
        this.apple = new Apple(this);
    }
    
    public char getSnakeDirection(){
        return snake.getDirection();
    }
    
    public LinkedList<HighScore> getHighScores(){
        return this.highScores;
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
