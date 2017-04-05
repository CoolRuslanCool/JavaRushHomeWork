package com.javarush.task.task27.task2712.statistic.event;

import java.util.Date;

/**
 * Created by ruslan on 20.03.17.
 */
public interface EventDataRow {
    EventType getType();
    Date getDate();
    int getTime();
}
