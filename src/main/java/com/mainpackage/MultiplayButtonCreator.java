package com.mainpackage;

import com.mainpackage.buttons.DifficultyButton;

import javax.swing.*;
import java.awt.*;

public class MultiplayButtonCreator extends ButtonFactory {
    @Override
    public JButton createButton() {
        JButton multiplayButton = new DifficultyButton("<html>Human vs Human</html>");
        multiplayButton.setBounds(0, 0, 100, 200);
        multiplayButton.setBackground(new Color(255, 220, 220));
        multiplayButton.setPreferredSize(multiplayButton.getSize());

        return multiplayButton;
    }
}
