package com.mainpackage;

import com.mainpackage.buttons.UndoButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UndoButtonCreator extends ButtonFactory {
    @Override
    public JButton createButton() {
        JButton restartButton = new UndoButton("Undo Move");
        restartButton.setBounds(360, 510, 140, 30);
        restartButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                GamePanel panel = GamePanel.getPanel();
                panel.undoMove();
            }
        });

        return restartButton;
    }
}
