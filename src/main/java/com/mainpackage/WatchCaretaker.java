package com.mainpackage;

import java.util.ArrayList;
import java.util.List;

public class WatchCaretaker {
    private List<WatchMemento> states = new ArrayList<>();

    public void push(WatchMemento state) {
        states.add(state);
    }

    public WatchMemento pop() {
        int lastIndex = states.size() - 1;
        return states.remove(lastIndex);
    }

    public WatchMemento getWatches(int index) {
        return states.get(index);
    }

    public WatchMemento getLastWatches() {
        int lastIndex = states.size() - 1;
        return states.get(lastIndex);
    }
}
