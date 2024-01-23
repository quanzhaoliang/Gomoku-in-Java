package com.mainpackage;

import com.mainpackage.buttons.DifficultyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HardDifficultyButtonCreator extends ButtonFactory{
    @Override
    public JButton createButton() {
        JButton hardDifficultyButton = new DifficultyButton("<html> Hard AI</html>");
        hardDifficultyButton.setBounds(0, 0, 100, 200);
        hardDifficultyButton.setBackground(new Color(255, 220, 220));
        hardDifficultyButton.setPreferredSize(hardDifficultyButton.getSize());

        return hardDifficultyButton;
    }
}
