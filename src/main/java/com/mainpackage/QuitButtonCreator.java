package com.mainpackage;

import com.mainpackage.buttons.QuitButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuitButtonCreator extends ButtonFactory{
    @Override
    public JButton createButton() {
        String quitText = "Quit";
        JButton quitButton = new QuitButton("<html>" + quitText + "</html>");
        quitButton.setBounds(2, 450, 80, 50);
        quitButton.setBackground(Colors.myOrange);
        quitButton.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        return quitButton;
    }
}
