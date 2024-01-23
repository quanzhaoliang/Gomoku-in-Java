package com.mainpackage;

public class Memento {

    private final int [][] content = new int[15][15];
    public Memento(int[][] content) {
        for(int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                this.content[i][j] = content[i][j];
            }
        }
    }

    public int[][] getContent() {
        return content;
    }


}