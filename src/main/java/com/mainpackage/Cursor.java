package com.mainpackage;

import java.awt.*;

public class Cursor {

    private int i = 0;
    private int j = 0;
    private int x = 0;
    private int y = 0;
    private int size = 20;
    private int occupied = 0; // 0 = not occupied, 1 = occupied


    private boolean isPresent = false;
    public Cursor(int i, int j, int x, int y){
        this.i = i;
        this.j = j;
        this.x = x;
        this.y = y;
    }

    public  void draw(Graphics g){
        if (isPresent){
            g.drawRect(x-size/2, y-size/2, size, size);
        }
    }

    //Check the mouse coordinate whether is within the cursor bound.
    public boolean showCursor(int x, int y){
        int x1 = this.x - this.size/2;
        int y1 = this.y - this.size/2;

        int x2 = this.x + this.size/2;
        int y2 = this.y + this.size/2;

        return x>x1 && y>y1 && x<x2 && y<y2;
    }


    public void setPresent(boolean present) {
        isPresent = present;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setOccupied(int occupied) {
        this.occupied = occupied;
    }

    public int getOccupied() {
        return occupied;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
