package Util;

import java.util.concurrent.TimeUnit;

/**
 * Created by ruixie on 11/02/2017.
 */
public class Timer {
    private long start;
    public Timer() {
        start = System.currentTimeMillis();
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getTimeForOperation() {
        return (System.currentTimeMillis() - this.start) / 1000 / 60;
    }
}
