package com.mainpackage;

import java.awt.image.BufferedImage;
import java.util.AbstractMap;
import java.util.Map;

public class PieceFlyweightFactory {

    private static final String color[] = {"Black", "White"}; // 1 is black, 2 is white
    private static final Map<String, BufferedImage> pngMap = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("Black", PieceImage.getBlackPiece()),
            new AbstractMap.SimpleEntry<>("White", PieceImage.getWhitePiece())
    );

    public static String getBlack(){
        return color[0];
    }

    public static String getWhite(){
        return color[1];
    }

    public static BufferedImage getImage(String color){
        return pngMap.get(color);
    }

}
