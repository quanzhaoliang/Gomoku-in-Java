package com.mainpackage.buttons;

import com.mainpackage.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicButton extends GameButton {
    public BasicButton(String text) {
        super(text);

        this.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        this.setBackground(Colors.myOrange);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("src/main/resources/buttonSound.wav");
            }
        });
    }
}
