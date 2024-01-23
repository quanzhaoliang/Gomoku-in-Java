package com.mainpackage;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class CountDown extends JLabel {
    private Timer timer;
    private final int period = 100;

    private LocalTime time = LocalTime.of(0, 5, 0, 0);

    private boolean active = false;

    private String seconds_string = String.format("%02d", time.getSecond());
    private String minutes_string = String.format("%02d", time.getMinute());

    public CountDown() {
        setText(minutes_string+":"+seconds_string);
        setFont(new Font("Verdana", Font.PLAIN, 16));
        setBorder(BorderFactory.createLineBorder(Color.black, 1));
        setOpaque(true);
        setHorizontalAlignment(JTextField.CENTER);
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void stop() {
        setActive(false);
        timer.cancel();
    }

    public void start() {
        setActive(true);
        timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                seconds_string = String.format("%02d", time.getSecond());
                minutes_string = String.format("%02d", time.getMinute());
                setText(minutes_string+":"+seconds_string);
                time = time.minusNanos((long) (period * Math.pow(10, 6)));
            }
        }, 0, period);
    }

    public void reset() {
        setActive(false);
        if(timer != null) stop();
        time = LocalTime.of(0, 5, 0, 0);
        seconds_string = String.format("%02d", time.getSecond());
        minutes_string = String.format("%02d", time.getMinute());
        setText(minutes_string+":"+seconds_string);
    }
}
