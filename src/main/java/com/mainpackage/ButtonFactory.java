package com.mainpackage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public abstract class ButtonFactory {
    public abstract JButton createButton();

    public void addButtonToPanel(JPanel panel) {
        JButton button = createButton();
        panel.add(button);
    }

    public void playSound(String path){
        try{
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
