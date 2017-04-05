package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.AdvertisementStorage;
import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

/**
 * Created by ruslan on 20.03.17.
 */
public class StatisticManager {
    private Set<Cook> cooks = new HashSet<>();
    private static StatisticManager statisticManager = new StatisticManager();
    private StatisticStorage storage
            = new StatisticStorage();

    private StatisticManager() {}

    public static StatisticManager getInstance() { return statisticManager; }

    public void register(EventDataRow data) {
        storage.put(data);
    }

    public void register(Cook cook) {
        cooks.add(cook);
    }

    public List<EventDataRow> getEventList(EventType type) {
        return storage.storage.get(type);
    }

    private static final int[] TIME_FIELDS =
            {
                    Calendar.HOUR_OF_DAY,
                    Calendar.HOUR,
                    Calendar.AM_PM,
                    Calendar.MINUTE,
                    Calendar.SECOND,
                    Calendar.MILLISECOND
            };

    public TreeMap<Date, Long> getAdvertisementRevenueAgregatedByDay() {
        TreeMap<Date, Long> result = new TreeMap<>();
        for (EventDataRow eventDataRow : storage.getEvents(EventType.SELECTED_VIDEOS)) {
            VideoSelectedEventDataRow vEventDataRow = (VideoSelectedEventDataRow) eventDataRow;
            GregorianCalendar gDate = new GregorianCalendar();
            gDate.setTime(vEventDataRow.getDate());
            for(int i : TIME_FIELDS)
                gDate.clear(i);
            Date date = gDate.getTime();
            Long dayRevenue = result.get(date) ;
            if (dayRevenue == null) dayRevenue = Long.valueOf(0);
            result.put(date, dayRevenue + vEventDataRow.getAmount());
        }
        return result;
    }

    public Map<Date, Map<String, Integer>> getCookWorkloading() {
        Map<Date, Map<String, Integer>> result = new TreeMap<>(Collections.reverseOrder());
        for (EventDataRow eventDataRow: storage.storage.get(EventType.COOKED_ORDER)) {
            CookedOrderEventDataRow event =  (CookedOrderEventDataRow) eventDataRow;
            register(new Cook(event.getCookName()));
            Date newDate = new Date(event.getDate().getYear(), event.getDate().getMonth(), event.getDate().getDate());
            String name = event.getCookName();
            if (result.containsKey(newDate)) {

                if (result.get(newDate).containsKey(name)) {
                    result.get(newDate).put(name, result.get(newDate).get(name) + event.getTime());
                } else result.get(newDate).put(name, event.getTime());
            } else {
                Map<String, Integer> temp = new TreeMap<>();
                temp.put(name, event.getTime());
                result.put(newDate, temp);
            }
        }
        return result;
    }

    public Set<Advertisement> getActiveVideoSet() {
        Set<Advertisement> res = new HashSet<>();
        for (Advertisement advertisement: AdvertisementStorage.getInstance().list()) {
            if (advertisement.getHits() >= 0)
                res.add(advertisement);
        }
        return res;
    }

    public Set<Advertisement> getArchivedVideoSet() {
        Set<Advertisement> res = new HashSet<>();
        for (Advertisement advertisement: AdvertisementStorage.getInstance().list()) {
            if (advertisement.getHits() <= 0)
                res.add(advertisement);
        }
        return res;
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage;

        private StatisticStorage() {
            storage = new HashMap<>();
            for (EventType type: EventType.values()) {
                storage.put(type, new ArrayList<>());
            }
        }

        private void put(EventDataRow date) {
            storage.get(date.getType()).add(date);
        }

        private List<EventDataRow> getEvents(EventType eventType) {
            return storage.get(eventType);
        }
    }
}
