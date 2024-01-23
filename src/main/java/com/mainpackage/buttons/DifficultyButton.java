package com.mainpackage.buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultyButton extends GameButton{
    public DifficultyButton(String text) {
        super(text);

        this.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("src/main/resources/buttonSound.wav");
            }
        });
    }
}
