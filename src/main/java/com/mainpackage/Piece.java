package com.mainpackage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Piece {


    private int x = 0;
    private int y = 0;
    private int size = 20;

    private PieceType type;

    // 1 = black piece, 2 = white piece

    public Piece(int x, int y, PieceType type){
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw(Graphics g){
        g.drawImage(type.getImage(), x ,y, size, size, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
