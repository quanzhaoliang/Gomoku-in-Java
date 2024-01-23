package com.mainpackage;

import com.mainpackage.buttons.BasicButton;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReturnToMainMenuButtonCreator extends ButtonFactory {
    @Override
    public JButton createButton() {
        String returnToMainMenuText = "Return to\n Main Menu";
        JButton returnToMenuButton = new BasicButton("<html>" + returnToMainMenuText.replaceAll("\\n", "<br>") + "</html>");
        returnToMenuButton.setBounds(2, 20, 90, 50);
        returnToMenuButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                GameFrame.getInstance().SetContentPane(1);

            }
        });

        return returnToMenuButton;
    }
}
