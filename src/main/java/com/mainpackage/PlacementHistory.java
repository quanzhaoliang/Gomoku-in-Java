package com.mainpackage;

import java.util.ArrayList;

public class PlacementHistory {
    private int count = 0;
    private ArrayList<String[]> movements = new ArrayList<>();

    public PlacementHistory(){}

    public void add(int xCoordinate, int yCoordinate) {
        String n = String.valueOf(this.count + 1);
        String p;
        if(this.count % 2 == 0) {
            p = "BLACK";
        }
        else {
            p = "WHITE";
        }
        String c = String.valueOf(xCoordinate);
        String r = String.valueOf(14 - yCoordinate);
        String[] data = {n, p, c, r};
        this.movements.add(data);
    }

    public String[] removeLast() {
        int last = getCount();
        countDec();
        return this.movements.remove(last);
    }

    public int size() {
        return this.count + 1;
    }
    public void countInc() {
        this.count++;
    }

    public void countDec() {
        this.count--;
    }

    public int getCount() {
        return this.count;
    }

    public void resetMovements() {
        this.movements = new ArrayList<>();
        this.count = 0;
    }
    public String[] getArrayElement(int index) {
        return this.movements.get(index);
    }
}
