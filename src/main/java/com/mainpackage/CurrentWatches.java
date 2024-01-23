package com.mainpackage;

public class CurrentWatches {
    private CountDown blackPlayerWatch;
    private CountDown whitePlayerWatch;
    public CurrentWatches() {
        this.blackPlayerWatch = new CountDown();
        this.whitePlayerWatch = new CountDown();
    }

    public CurrentWatches(CountDown blackPlayerWatch, CountDown whitePlayerWatch) {
        this.blackPlayerWatch = blackPlayerWatch;
        this.whitePlayerWatch = whitePlayerWatch;
    }

    public CountDown getBlackPlayerWatch() {
        return blackPlayerWatch;
    }

    public CountDown getWhitePlayerWatch() {
        return whitePlayerWatch;
    }

//    public void set(CountDown blackPlayerWatch, CountDown whitePlayerWatch, PlacementHistory placementHistory) {
//        this.blackPlayerWatch.setTime(blackPlayerWatch.getTime());
//        this.blackWatchActive = blackPlayerWatch.isActive();
//        this.whiteWatchTime = whitePlayerWatch.getTime();
//        this.whiteWatchActive = whitePlayerWatch.isActive();
//        this.placementHistory = placementHistory;
//    }

    public WatchMemento createMemento() {
        return new WatchMemento(blackPlayerWatch, whitePlayerWatch);
    }
    public void restoreMemento(WatchMemento watchMemento) {
        this.blackPlayerWatch.setTime(watchMemento.getBlackWatchTime());
        this.whitePlayerWatch.setTime(watchMemento.getWhiteWatchTime());
        this.blackPlayerWatch.setActive(watchMemento.isBlackWatchActive());
        this.blackPlayerWatch.setActive(watchMemento.isWhiteWatchActive());
    }
}
