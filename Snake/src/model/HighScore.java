package model;

import java.util.Comparator;

/**
 *
 * @author BusterK
 */
public class HighScore implements Comparable<HighScore>{
    private final int score;
    private final String name;

    public HighScore(int score, String name){
        this.score = score;
        this.name = name;
    }
    
    public int getScore(){
        return this.score;
    }
    
    public String getName(){
        return this.name;
    }

    @Override
    public int compareTo(HighScore o) {
        if(this.score < o.getScore())
            return 1;
        else if (this.score == o.getScore())
            return 0;
        else
            return -1;
    }
}
