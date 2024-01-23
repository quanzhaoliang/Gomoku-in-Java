package com.mainpackage;

import com.mainpackage.buttons.BasicButton;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RulesButtonCreator extends ButtonFactory {
    public JButton createButton() {
        String rulesText = "Rules";
        JButton rulesButton = new BasicButton("<html>" + rulesText + "</html>");
        rulesButton.setBounds(2, 90, 80, 30);
        rulesButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                GameFrame.getInstance().SetContentPane(2);
            }
        });

        return rulesButton;
    }
}
