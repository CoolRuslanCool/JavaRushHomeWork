package com.javarush.task.task27.task2712.statistic.event;

import java.util.Date;

/**
 * Created by ruslan on 20.03.17.
 */
public class NoAvailableVideoEventDataRow implements EventDataRow {
    private int totalDuration;
    private Date currentDate = new Date();

    public NoAvailableVideoEventDataRow(int totalDuration) {
        this.totalDuration = totalDuration;
    }

    @Override
    public Date getDate() {
        return currentDate;
    }

    @Override
    public int getTime() {
        return totalDuration;
    }

    @Override
    public EventType getType() {
        return EventType.NO_AVAILABLE_VIDEO;
    }
}
