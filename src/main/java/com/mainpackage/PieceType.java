package com.mainpackage;

import java.awt.image.BufferedImage;

public class PieceType {
    private String color;
    private BufferedImage image;


    public PieceType(String color, BufferedImage image){
        this.color = color;
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getColor() {
        return color;
    }
}
