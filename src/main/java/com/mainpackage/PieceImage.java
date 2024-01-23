package com.mainpackage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class PieceImage {

    public static BufferedImage whitePiece = null;

    public static BufferedImage blackPiece = null;

    //load the piece images
    public static void load(){
        try{
            blackPiece = ImageIO.read(PieceImage.class.getResource("/black.png"));
            whitePiece = ImageIO.read(PieceImage.class.getResource("/white.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static BufferedImage getWhitePiece() {
        return whitePiece;
    }

    public static BufferedImage getBlackPiece() {
        return blackPiece;
    }

}