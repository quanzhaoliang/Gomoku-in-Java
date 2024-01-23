package com.mainpackage;

import com.mainpackage.buttons.QuitButton;

import javax.swing.*;
import java.awt.*;

public class MenuQuitButtonCreator extends ButtonFactory {
    @Override
    public JButton createButton() {
        String quitText = "Quit";
        JButton quitButton = new QuitButton("<html>" + quitText + "</html>");
        quitButton.setBounds(0, 0, 100, 50);
        quitButton.setBackground(new Color(255, 220, 220));
        quitButton.setPreferredSize(quitButton.getSize());

        return quitButton;
    }
}

