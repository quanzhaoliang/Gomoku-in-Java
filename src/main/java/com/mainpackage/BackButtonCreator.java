package com.mainpackage;

import com.mainpackage.buttons.BasicButton;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BackButtonCreator extends ButtonFactory {
    @Override
    public JButton createButton() {
        String backText = "Back to Game";
        JButton backButton = new BasicButton("<html>" + backText.replaceAll("\\n", "<br>") + "</html>");
        backButton.setBounds(2, 500, 100, 50);
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                GameFrame.getInstance().SetContentPane(0);
            }
        });
        return backButton;
    }
}
