package model;

/**
 *
 * @author BusterK
 */
public class Model {
    private char[][] field;
    private Snake snake;
    
    public Model(int x, int y){
        this.field = new char[x][y];
        this.snake = new Snake(x/2,y/2);
    }
}
