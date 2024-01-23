package com.mainpackage;

import javax.swing.*;
import java.awt.*;


public class GameFrame extends JFrame {
    private static GameFrame instance;
    private Container[] containerArray;
    private int currentContainerIndex;

    public GameFrame(Container[] containerArray) {
        if(instance != null) {
            System.err.println("There can only be one GameFrame instance");
        }

        instance = this;
        this.containerArray = containerArray;
        currentContainerIndex = 0;

        setTitle("HEDGEHOG - GOMOKU");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    public void SetContentPane(int index) {
        this.setContentPane(containerArray[index]);
        currentContainerIndex = index;
        // Tells AWT that the component tree hierarchy has changed and updates
        containerArray[index].revalidate();
        repaint();
    }

    /**
     * Gets currently displayed container. Useful for adding popups.
     * @return currentContainerIndex
     */
    public Container GetCurrentContainer() {
        return containerArray[currentContainerIndex];
    }

    public static GameFrame getInstance() {
        return instance;
    }

}
