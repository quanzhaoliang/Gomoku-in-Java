package com.mainpackage;

import com.mainpackage.buttons.BasicButton;

import javax.swing.*;

public class CloseButtonCreator extends ButtonFactory{
    @Override
    public JButton createButton() {
        JButton closeButton = new BasicButton("<html> X </html>");
        closeButton.setBounds(0, 0, 45, 45);
        closeButton.setBackground(Colors.myOrange);
        closeButton.setPreferredSize(closeButton.getSize());

        return closeButton;
    }
}
