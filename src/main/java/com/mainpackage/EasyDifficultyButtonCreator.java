package com.mainpackage;

import com.mainpackage.buttons.DifficultyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EasyDifficultyButtonCreator extends ButtonFactory {
    @Override
    public JButton createButton() {
        JButton difficultyButton = new DifficultyButton("<html> Easy AI</html>");
        difficultyButton.setBounds(0, 0, 100, 200);
        difficultyButton.setBackground(new Color(220, 255, 220));
        difficultyButton.setPreferredSize(difficultyButton.getSize());

        return difficultyButton;
    }
}

