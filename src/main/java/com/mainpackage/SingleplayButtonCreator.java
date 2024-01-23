package com.mainpackage;

import com.mainpackage.buttons.DifficultyButton;

import javax.swing.*;
import java.awt.*;

public class SingleplayButtonCreator extends ButtonFactory {
    @Override
    public JButton createButton() {
        JButton singleplayButton = new DifficultyButton("<html>Human vs AI</html>");
        singleplayButton.setBounds(0, 0, 100, 200);
        singleplayButton.setBackground(new Color(255, 220, 220));
        singleplayButton.setPreferredSize(singleplayButton.getSize());

        return singleplayButton;
    }
}
