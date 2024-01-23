package com.mainpackage.buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuitButton extends GameButton {
    public QuitButton(String text) {
        super(text);

        this.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("src/main/resources/buttonSound.wav");
            }
        });
    }
}
