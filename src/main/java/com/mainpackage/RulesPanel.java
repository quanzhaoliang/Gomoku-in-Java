package com.mainpackage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RulesPanel extends JPanel {
    private static RulesPanel panel;

    private JLabel rulesLabel;
    private JLabel textLabel;

    Font titleFont = null;
    Font textFont = null;


    public RulesPanel() {

        // Import fonts
        try {
            textFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/RobotoMono-Light.ttf")).deriveFont(12f);
            titleFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/RobotoMono-Bold.ttf")).deriveFont(12f);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            // Register the fonts
            ge.registerFont(textFont);
            ge.registerFont(titleFont);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
        this.setLayout(null);
        this.setBackground(Colors.myDarkGrey);
        this.panel = this;
        createText();
        ButtonFactory creator = new BackButtonCreator();
        creator.addButtonToPanel(panel);
    }

    public void createText() {
        rulesLabel = new JLabel("<html><h1 " +
                "style='text-align:left; width:200px;font-size:34px'>" +
                "<FONT COLOR=#fecb87" +
                "<strong>Rules</strong>" +
                "</h1><hr></html>");
        rulesLabel.setFont(titleFont);

        rulesLabel.setBounds(50, 50, 250, 90);
        this.add(rulesLabel);

        textLabel = new JLabel("<html><h2 " +
                "style='text-align:left; width:450px;font-size:11px;padding-left: 20px;'>" +
                "<FONT COLOR=black>" +
                "1. Black plays first. Black starts the game by placing a black piece on any intersection of the board." +
                "<br><br>" +
                "2. Black and white shall alternate placing pieces of their respective color on empty intersections of the board."+
                "<br><br>" +
                "3. A player wins if they are first to create an unbroken line of five pieces of their color horizontally, vertically, or diagonally." +
                "<br><br>" +
                "4. If the board is completely filled (no empty intersections) and neither player has won, then the game ends in a draw."+
                "<br><br>" +
                "Good luck!" +
                "</h2></html>");
        textLabel.setFont(textFont);

        textLabel.setBounds(50, 150, 600, 310);
        textLabel.setBackground(Colors.myLightGrey);
        textLabel.setOpaque(true);
        this.add(textLabel);

    }

}


