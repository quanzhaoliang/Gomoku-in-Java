package com.mainpackage;

import javax.swing.*;

public abstract class Panel extends JPanel {
    protected abstract void createLabel();

    public abstract void resetPanel();
    public abstract void undoPanel();
}
