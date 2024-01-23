package com.mainpackage;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class PieceFactory {
    private  static final HashMap pieceMap = new HashMap<>();

    public static PieceType createPiece(String color, BufferedImage image){
        StringBuilder sb = new StringBuilder();
        sb.append(color);
        sb.append(image);
        String pieceCacheKey = sb.toString();
        PieceType result = (PieceType) pieceMap.get(pieceCacheKey);

        if (result == null){
            result = new PieceType(color, image);
            pieceMap.put(pieceCacheKey, result);
        }

        return result;
    }

}
