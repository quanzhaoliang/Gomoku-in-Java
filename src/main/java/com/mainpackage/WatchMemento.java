package com.mainpackage;

import java.time.LocalTime;

public class WatchMemento {
    private LocalTime blackWatchTime;
    private boolean blackWatchActive;
    private LocalTime whiteWatchTime;
    private boolean whiteWatchActive;

    public WatchMemento(CountDown blackPlayerWatch, CountDown whitePlayerWatch) {
        this.blackWatchTime = blackPlayerWatch.getTime();
        this.blackWatchActive = blackPlayerWatch.isActive();
        this.whiteWatchTime = whitePlayerWatch.getTime();
        this.whiteWatchActive = whitePlayerWatch.isActive();
    }

    public LocalTime getBlackWatchTime() {
        return blackWatchTime;
    }

    public boolean isBlackWatchActive() {
        return blackWatchActive;
    }

    public LocalTime getWhiteWatchTime() {
        return whiteWatchTime;
    }

    public boolean isWhiteWatchActive() {
        return whiteWatchActive;
    }


}
