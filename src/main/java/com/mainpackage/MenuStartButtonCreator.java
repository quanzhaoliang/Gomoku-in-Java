package com.mainpackage;

import com.mainpackage.buttons.StartButton;

import javax.swing.*;
import java.awt.*;

public class MenuStartButtonCreator extends ButtonFactory {
    @Override
    public JButton createButton() {
        String startText = "Start";
        JButton startButton = new StartButton("<html>" + startText + "</html>");
        startButton.setBounds(0, 0, 150, 75);
        startButton.setBackground(Colors.myOrange);
        startButton.setPreferredSize(startButton.getSize());

        return startButton;
    }
}
