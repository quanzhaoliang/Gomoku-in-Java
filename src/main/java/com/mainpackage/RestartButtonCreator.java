package com.mainpackage;

import com.mainpackage.buttons.BasicButton;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RestartButtonCreator extends ButtonFactory {
    @Override
    public JButton createButton() {
        String restartText = "Restart";
        JButton restartButton = new BasicButton("<html>" + restartText + "</html>");
        restartButton.setBounds(2, 140, 80, 30);
        restartButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                GamePanel panel = GamePanel.getPanel();
                panel.resetGame();
            }
        });

        return restartButton;
    }
}
