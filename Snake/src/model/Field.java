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
public class Field {
    private int x;
    private int y;

    public int getRow() {
            return x;
    }

    public int getColumn() {
            return y;
    }
    
    public void Move(int x, int y){
        this.x = x;
        this.y = y;
    }
}
