package com.mainpackage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class WinPopup extends JDialog {


    public WinPopup(JFrame parentFrame, int player) {

        super(parentFrame, "Result", true);
        setSize(350, 300);
        setResizable(false);
        setLayout(new BorderLayout());
        parentFrame.getContentPane().setBackground(Colors.myDarkGrey);
        this.setBackground(Colors.myDarkGrey);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        GamePanel panel = GamePanel.getPanel();

        Font titleFont = null;

        try {
            titleFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/NotoSans-BlackItalic.ttf")).deriveFont(12f);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            // Register the fonts
            ge.registerFont(titleFont);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }

        // Add a message label
        if (player == -1){
            JLabel messageLabel = new JLabel("<html><h1 " +
                    "style='text-align:center; width:200px;font-size:18px'>" +
                    "<FONT COLOR=white" +
                    "<strong>Congratulations,<br>you won!</strong>" +
                    "</h1></html>", JLabel.CENTER);
            messageLabel.setFont(titleFont);
            messageLabel.setBackground(Colors.myDarkGrey);
            messageLabel.setOpaque(true);
            add(messageLabel, BorderLayout.CENTER);
        }
        else {
            JLabel messageLabel = new JLabel("<html><h1 " +
                    "style='text-align:center; width:200px;font-size:16px'>" +
                    "<FONT COLOR=white" +
                    "<strong>Sorry,<br>you lost!</strong>" +
                    "</h1><hr></html>", JLabel.CENTER);
            messageLabel.setFont(titleFont);
            messageLabel.setBackground(Colors.myDarkGrey);
            messageLabel.setOpaque(true);
            messageLabel.setPreferredSize(new Dimension(170, 50));
            add(messageLabel, BorderLayout.CENTER);
        }

        // Create buttons for options
        JButton returnToMainMenuButton = new JButton("Return to Main Menu");
        returnToMainMenuButton.setPreferredSize(new Dimension(170, 25));
        returnToMainMenuButton.setBackground(Colors.myOrange);
        returnToMainMenuButton.setOpaque(true);
        returnToMainMenuButton.setBorderPainted(true);
        returnToMainMenuButton.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.setPreferredSize(new Dimension(90, 25));
        playAgainButton.setBackground(Colors.myOrange);
        playAgainButton.setOpaque(true);
        playAgainButton.setBorderPainted(true);
        playAgainButton.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        JButton quitButton = new JButton("Quit");
        quitButton.setPreferredSize(new Dimension(90, 25));
        quitButton.setBackground(Colors.myRed);
        quitButton.setOpaque(true);
        quitButton.setBorderPainted(true);
        quitButton.setBorder(BorderFactory.createLineBorder(Color.black, 1));


        // Add action listeners to the buttons
        returnToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("src/main/resources/buttonSound.wav");
                // Handle the "Return to Main Menu" option
                GameFrame.getInstance().SetContentPane(1);
                dispose(); // Close the dialog
            }
        });

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("src/main/resources/buttonSound.wav");
                // Handle the "Play Again" option
                panel.resetGame();
                // Implement the code to start a new game here
                dispose(); // Close the dialog
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the "Play Again" option
                playSound("src/main/resources/buttonSound.wav");
                dispose(); // Close the dialog
                System.exit(0);
            }
        });


        // Create a panel to hold the buttons and add them to the dialog
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,0,0,10);
        buttonPanel.add(returnToMainMenuButton, c);
        c.gridx = 1;
        buttonPanel.add(playAgainButton, c);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.insets = new Insets(10,0,10,0);
        buttonPanel.add(quitButton, c);
        add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setBackground(Colors.myDarkGrey);


        // Center the dialog on the parent frame
        setLocationRelativeTo(parentFrame);
    }

    public void playSound(String path){
        try{
            File soundFile = new File(path);
            AudioInputStream audio = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
