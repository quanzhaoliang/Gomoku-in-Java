package com.mainpackage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Landing page and Main menu for the Gomoku Game
 */
public class MainMenuPanel extends JPanel {
    @Override
    public boolean isOptimizedDrawingEnabled() {
        return false;
    }

    /**
     * Creates the main menu panel for Gomoku.
     */
    public MainMenuPanel() {
        // Create GridBagLayout inside the MainMenuPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Colors.myDarkGrey);

        JPanel gameModePanel = new GameModePopup();
        gameModePanel.setVisible(false);


        setBackground(Colors.myDarkGrey);
        setLayout(new OverlayLayout(this));

        add(gameModePanel);
        add(mainPanel);

        // Import fonts
        Font titleFont = null;
        Font buttonTextFont = null;
        try {
            buttonTextFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/RobotoMono-Medium.ttf")).deriveFont(12f);
            titleFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/NotoSans-BlackItalic.ttf")).deriveFont(12f);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            // Register the fonts
            ge.registerFont(buttonTextFont);
            ge.registerFont(titleFont);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }

        // Create constraints for formatting of the title
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(50,0,0,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        // Title with css styling
        JLabel titleLabel = new JLabel("<html><h1 " +
                "style='text-align:center; width:200px;font-size:30px'>" +
                "<FONT COLOR=#fecb87>" +
                "<strong>GOMOKU</strong>" +
                "</h1><hr></html>");
        titleLabel.setFont(titleFont);
        mainPanel.add(titleLabel, gbc);

        // Create constraints for formatting of buttons
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20,0,0,0);

        // Create Buttons
        JPanel buttons = new JPanel(new GridBagLayout());
        buttons.setBackground(Colors.myDarkGrey);
        ButtonFactory buttonFactory = new MenuStartButtonCreator();
        JButton startButton = buttonFactory.createButton();
        startButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                gameModePanel.setVisible(true);
            }
        });

        buttons.add(startButton, gbc);

        buttonFactory = new MenuQuitButtonCreator();
        JButton quitButton = buttonFactory.createButton();
        quitButton.setFont(buttonTextFont);
        buttons.add(quitButton, gbc);


        mainPanel.add(buttons, gbc);
    }

}
