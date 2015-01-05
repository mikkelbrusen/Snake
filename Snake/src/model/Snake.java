/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author BusterK
 */
public class Snake {
    Field currentPosition;
    
    public void move(char dir){
        switch(dir){
            case 'N':
                walk(0, 1);
                break;
            case 'S':
                walk(0, -1);
                break;
            case 'E':
                walk(1, 0);
                break;
            case 'W':
                walk(-1, 0);
                break;
        }        
    }
    private void walk(int x, int y){
        if(isReverseDirection(x,y))
            currentPosition.Move(0, 0);
        else
            currentPosition.Move(x, y);
    }
    
    private boolean isReverseDirection(int x, int y){
        if ( x == currentPosition.getX()-1 || 
                y == currentPosition.getY()-1 )
            return true;
        else
            return false;
    }
}
