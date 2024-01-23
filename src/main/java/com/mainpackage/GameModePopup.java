package com.mainpackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameModePopup extends JPanel {
    @Override
    public boolean isOptimizedDrawingEnabled() {
        return false;
    }

    private static JPanel root;

    public GameModePopup() {
        setLayout(new OverlayLayout(this));
        setOpaque(false);
        setMaximumSize(new Dimension(800, 600));
        StartGamePopup difficultySelect = new StartGamePopup();
        difficultySelect.setVisible(false);
        add(difficultySelect);
        // Blocks any mouse listeners behind of this panel
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            }
        });

        JPanel itemContainer = new JPanel();
        itemContainer.setBackground(new Color(50, 50, 50, 150));
        itemContainer.setMaximumSize(new Dimension(600, 300));
        add(itemContainer);

        itemContainer.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        gbc.insets = new Insets(0,0,10,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Close button
        ButtonFactory buttonFactory = new CloseButtonCreator();
        JButton closeButton = buttonFactory.createButton();

        root = this;
        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                root.setVisible(false);
            }
        });

        itemContainer.add(closeButton, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0,closeButton.getWidth(),0,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;

        buttonFactory = new SingleplayButtonCreator();
        JButton singleplayButton = buttonFactory.createButton();
        singleplayButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                GamePanel.getPanel().setMultiplay(false);
                difficultySelect.setVisible(true);
            }
        });

        itemContainer.add(singleplayButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0,0,0,0);
        buttonFactory = new MultiplayButtonCreator();
        JButton multiplayButton = buttonFactory.createButton();
        multiplayButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                GamePanel.getPanel().setMultiplay(true);
                GamePanel.getPanel().resetGame();
                GameFrame.getInstance().SetContentPane(0);
                root.setVisible(false);
            }
        });

        itemContainer.add(multiplayButton, gbc);
    }

    public static JPanel getRoot() {
        return root;
    }
}