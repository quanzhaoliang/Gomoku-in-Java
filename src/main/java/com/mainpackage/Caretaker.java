package com.mainpackage;

import java.lang.reflect.Array;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class Caretaker {
    private List<Memento> states = new ArrayList<>();
    public void push(Memento state) {
        states.add(state);
    }
    public Memento pop() {
        int lastIndex = states.size() - 1;
        Memento lastState = states.get(lastIndex);
        states.remove(lastState);
        BoardMatrix.setZero();

        lastIndex = states.size() - 1;
        Memento desiredState  = states.get(lastIndex);

        return desiredState;
    }


}