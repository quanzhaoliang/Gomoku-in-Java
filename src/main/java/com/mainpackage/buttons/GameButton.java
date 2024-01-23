package com.mainpackage.buttons;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public abstract class GameButton extends JButton {
    public GameButton(String text) {
        super(text);
        Font RobotoMono= null;
        try {
            RobotoMono = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/RobotoMono-Medium.ttf")).deriveFont(13f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            // Register the fonts

            ge.registerFont(RobotoMono);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }

        // Makes visuals consistent between different Operating Systems
        this.setFont(RobotoMono);
        this.setOpaque(true);
        this.setBorderPainted(true);
    }

    protected void playSound(String path){
        try {
            File soundFile = new File(path);
            AudioInputStream audio = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
