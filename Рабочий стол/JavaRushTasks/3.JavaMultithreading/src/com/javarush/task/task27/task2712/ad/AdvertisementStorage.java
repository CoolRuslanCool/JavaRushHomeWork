package com.javarush.task.task27.task2712.ad;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ruslan on 19.03.17.
 */
public class AdvertisementStorage {
    private final List<Advertisement> videos = new LinkedList();
    private static AdvertisementStorage storage = new AdvertisementStorage();

    private AdvertisementStorage() {
        Object someContent = new Object();
        add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60));
        add(new Advertisement(someContent, "Second Video", 100, 10, 15 * 60));
        add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60));
    }

    public static AdvertisementStorage getInstance() {
        return storage;
    }

    public List<Advertisement> list() { return videos; }

    public void add(Advertisement advertisement) {
        videos.add(advertisement);
    }
}
