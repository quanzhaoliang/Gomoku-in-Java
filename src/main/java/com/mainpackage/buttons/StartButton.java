package com.mainpackage.buttons;

import com.mainpackage.GameFrame;
import com.mainpackage.StartGamePopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartButton extends GameButton{

    public StartButton(String text) {
        super(text);

        this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("src/main/resources/startButton.wav");
            }
        });
    }
}
