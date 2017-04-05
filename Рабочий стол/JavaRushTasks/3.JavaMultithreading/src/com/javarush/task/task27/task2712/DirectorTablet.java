package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ruslan on 21.03.17.
 */
public class DirectorTablet {

    public void printAdvertisementProfit() {
        List<EventDataRow> list = StatisticManager.getInstance().getEventList(EventType.SELECTED_VIDEOS);
        double total = 0d;
        if (list != null) {
            if (list.size() > 0) {
                Collections.sort(list, new Comparator<EventDataRow>() {
                    @Override
                    public int compare(EventDataRow o1, EventDataRow o2) {
                        return o2.getDate().compareTo(o1.getDate());
                    }
                });
                String date, currentDate = new SimpleDateFormat("d-MMM-yyyy", Locale.ENGLISH).format(list.get(0).getDate());
                long amountPerDay = 0, amount;
                for (int i = 0; i<list.size();i++) {
                    VideoSelectedEventDataRow event = (VideoSelectedEventDataRow) list.get(i);
                    date = new SimpleDateFormat("d-MMM-yyyy", Locale.ENGLISH).format(event.getDate());
                    amount = event.getAmount();
                    total += amount;
                    if (date.equals(currentDate)) {
                        amountPerDay += amount;
                    } else {
                        ConsoleHelper.writeMessage(String.format("%s - %.2f", currentDate, (double) amountPerDay / 100).replace(',', '.'));
                        currentDate = date;
                        amountPerDay = amount;
                    }
                    if (i == list.size()-1) {
                        ConsoleHelper.writeMessage(String.format("%s - %.2f", currentDate, (double) amountPerDay / 100).replace(',', '.'));
                    }
                }
            }
        }
        ConsoleHelper.writeMessage(String.format("Total - %.2f", total / 100).replace(',', '.'));

    }

    public void printCookWorkloading() {
        for (Map.Entry<Date, Map<String, Integer>> date: StatisticManager.getInstance().getCookWorkloading().entrySet()) {
            ConsoleHelper.writeMessage(new SimpleDateFormat("d-MMM-yyyy", Locale.ENGLISH).format(date.getKey()));
            for (Map.Entry<String, Integer> pair: date.getValue().entrySet()) {
                ConsoleHelper.writeMessage(pair.getKey() + " - " + pair.getValue()/60 + " min");
            }
        }
    }

    public void printActiveVideoSet() {
        ConsoleHelper.writeMessage("ActiveVideo:");
        for (Advertisement advertisement: StatisticManager.getInstance().getActiveVideoSet()) {
            System.out.println(advertisement.getName() + " hits: " + advertisement.getHits());
        }
    }

    public void printArchivedVideoSet() {
        ConsoleHelper.writeMessage("ArchivedVideo:");
        for (Advertisement advertisement: StatisticManager.getInstance().getArchivedVideoSet()) {
            System.out.println(advertisement.getName() + " hits: " + advertisement.getHits());
        }
    }
}
