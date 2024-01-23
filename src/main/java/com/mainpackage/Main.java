package com.mainpackage;

import javax.swing.*;
import java.awt.*;

public class Main  {

    public static void main(String[] args){
        GamePanel panel = new GamePanel();
        MainMenuPanel mainMenu = new MainMenuPanel();
        RulesPanel rulesPanel = new RulesPanel();
        GameFrame frame = new GameFrame(new Container[]{panel, mainMenu, rulesPanel});
        frame.setContentPane(mainMenu);
        frame.setVisible(true);
    }
}
