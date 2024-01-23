package com.mainpackage.buttons;

import com.mainpackage.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class UndoButton extends GameButton{
    public UndoButton(String text) {
        super(text);
        Font RobotoMono_Bold = null;
        try {
            RobotoMono_Bold= Font.createFont(Font.TRUETYPE_FONT, new File("fonts/RobotoMono-Bold.ttf")).deriveFont(16f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            // Register the fonts

            ge.registerFont(RobotoMono_Bold);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
        this.setFont(RobotoMono_Bold);
        this.setBackground(Colors.myRed);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("src/main/resources/undoSound.wav");
            }
        });
    }
}
