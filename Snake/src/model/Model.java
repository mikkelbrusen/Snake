package model;

import AI.AI;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Creates a new game model with a game field of size n x m, a snake in the middle, and a random apple.
 * Valid public functions are:
 * getGameField() - Returns a array of type Enumerators which is an enumerator.
 * moveSnake(char) - Moves the snake in direction N, E, S or W.
 */
public class Model {
    //###################################################
    //#                                                 #
    //#          Variables and constructor              #
    //#                                                 #
    //###################################################
    private static final int MAX_WORMHOLES = 10;
    private static final int MAX_HIGHSCORES = 2;
    private String fileName;
    private final Audio audio;
    private final LinkedList<HighScore> highScores;
    private final Field[] wormHoles;
    private boolean useAI, hasUsedAI, pause, gameOver;
    private int score, theme;
    private AI ai;
    private Snake snake;
    private Dimension dimension;
    private LinkedList<Field> availableFields;
    private Field[][] gameField;
    
    public Model(Dimension dimension, String fileName){
        this.useAI = false;
        this.availableFields = new LinkedList<>();
        this.highScores = new LinkedList<>();
        this.wormHoles = new Field[MAX_WORMHOLES*2];
        this.fileName = fileName;
        this.dimension = dimension;
        this.theme = 0;
        this.audio = new Audio();
        for(int i = 0; i < MAX_HIGHSCORES; i++){
            setNewHighScore("AI");
        }

        doReset();
    }
    
    //###################################################
    //#                                                 #
    //#                 Model State                     #
    //#                                                 #
    //###################################################

    public void setPaused(){
        this.pause = !pause;
        if (pause){
        	audio.stopMusic();
        }
        else {
        	audio.startMusic();
        }
    }

    public void setNewHighScore(String name) {
        if (!(hasUsedAI)) {
            this.highScores.add(new HighScore(this.score, name));
            Collections.sort(highScores);
            if (highScores.size() > MAX_HIGHSCORES) {
                highScores.removeLast();
            }
        }
    }

    void setGameOver() {
        this.gameOver = true;
        audio.stopAll();
        audio.stopMusic();
        audio.playSound(3);
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public boolean isPaused() {
        return pause;
    }

    public void setPaused(boolean b) {
        this.pause = b;
        if (pause) {
            audio.stopMusic();
        } else {
            audio.startMusic();
        }
    }

    public int getScore(){
        return this.score;
    }

    public int getLowestHighScore(){
        try{
            return this.highScores.getLast().getScore();
        }catch(NoSuchElementException e){
            return 0;
        }
    }

    void newApple() {
        this.score += 1;
        if (score % 10 == 0){
            audio.playSound(2);
        }
        else {
            audio.playSound(1);
        }
        new Apple(this);
    }

    public void playStartAudio(){
        audio.stopAll();
        audio.playSound(4);
        audio.startMusic();
    }

    public LinkedList<HighScore> getHighScores(){
        return this.highScores;
    }

    //###################################################
    //#                                                 #
    //#             Artificial Intelligence             #
    //#                                                 #
    //###################################################
    public void setUseAI(boolean b){
        this.hasUsedAI = true;
        this.useAI = b;
    }

    public boolean isNotUsingAI() {
        return !this.useAI;
    }

    public boolean getHasUsedAI(){
        return this.hasUsedAI;
    }

    //###################################################
    //#                                                 #
    //#                  Game field                     #
    //#                                                 #
    //###################################################
    public void setTrack(String fileName) {
    	this.fileName = fileName;
    }
    
    public Field[][] getGameField(){
        return this.gameField;
    }

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
                    field.setType(Enumerators.BLANK);
                    availableFields.addFirst(field);
                    gameField[i][j] = field;
                }
            }
        }
        this.snake = new Snake(this);
        new Apple(this);
        this.score = 0;
        this.ai = new AI(this);
    }

    private boolean loadTrack(String fileName){
    	System.out.println("Try to load filename " + fileName);
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
                        field.setType(Enumerators.WALL);
                    }
                    else if(Character.isDigit(line.charAt(i))){
                        field.setType(Enumerators.WORMHOLE);
                        field.setWhNumber(line.charAt(i));
                        wormHoles[whcount] = field;
                        whcount++;
                    }
                    else{
                        field.setType(Enumerators.BLANK);
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

    void setFieldValue(Enumerators val, Field field) {
        if(field.getType() != Enumerators.WORMHOLE){
            switch(val){
                case BLANK:
                    this.gameField[field.getWidth()][field.getHeight()].setType(Enumerators.BLANK);
                    if (!(this.availableFields.contains(field)))
                        this.availableFields.add(field);
                    break;
                case APPLE:
                    this.gameField[field.getWidth()][field.getHeight()].setType(Enumerators.APPLE);
                    while(this.availableFields.contains(field))
                        this.availableFields.remove(field);
                    break;
                case SNAKE:
                    this.gameField[field.getWidth()][field.getHeight()].setType(Enumerators.SNAKE);
                    while(this.availableFields.contains(field))
                        this.availableFields.remove(field);
                    break;
                case WALL:
                    this.gameField[field.getWidth()][field.getHeight()].setType(Enumerators.WALL);
                    while(this.availableFields.contains(field))
                        this.availableFields.remove(field);
                    break;
                case HEAD:
                    this.gameField[field.getWidth()][field.getHeight()].setType(Enumerators.HEAD);
                    while(this.availableFields.contains(field))
                        this.availableFields.remove(field);
                    break;
                case TAIL:
                    this.gameField[field.getWidth()][field.getHeight()].setType(Enumerators.TAIL);
                    while(this.availableFields.contains(field))
                        this.availableFields.remove(field);
                    break;
            }
        }
    }

    public LinkedList<Field> getAvailableFields(){
        return this.availableFields;
    }

    public int getTheme(){
        return this.theme;
    }

    public void setTheme(int i) {
        if (i == 1 || i == 2) {
            this.theme = i;
        }
    }

    public Dimension getDimension(){
        return this.dimension;
    }

    Field[] getWormHoles() {
        return this.wormHoles;
    }

    //###################################################
    //#                                                 #
    //#                 The Snake                       #
    //#                                                 #
    //###################################################
    public Field getSnakePosition(){
        return snake.getPosition();
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

    public void moveSnake() {
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
    public char getSnakeDirection(){
        return snake.getDirection();
    }
}

