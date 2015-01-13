package model;

import java.util.Comparator;

/**
 *
 * @author BusterK
 */
public class HighScore {
    private int score;
    private String name;
    
    public void HighScore(int score, String name){
        this.score = score;
        this.name = name;
    }
    
    public int getScore(){
        return this.score;
    }
    
    public String getName(){
        return this.name;
    }
}

class CustomComparator implements Comparator<HighScore> {
    @Override
    public int compare(HighScore o1, HighScore o2) {
        if(o1.getScore() >= o2.getScore()){
            return 0;
        }
        return 1;
    }
}
