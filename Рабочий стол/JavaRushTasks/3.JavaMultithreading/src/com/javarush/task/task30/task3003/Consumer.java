package com.javarush.task.task30.task3003;

import java.util.concurrent.TransferQueue;

/**
 * Created by ruslan on 25.03.17.
 */
public class Consumer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Consumer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(450);
        } catch (InterruptedException ignored) {}
            while (true) {
                try {
                    System.out.format("Processing %s\n", queue.take().toString());
                } catch (InterruptedException ignored) {return;}
            }

    }
}
