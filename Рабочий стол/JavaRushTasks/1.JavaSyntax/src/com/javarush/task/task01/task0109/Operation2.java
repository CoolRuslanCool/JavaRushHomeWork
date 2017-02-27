package com.javarush.task.task01.task0109;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by ruslan on 22.02.17.
 */
public class Operation2 {
    private static ExecutorService service = Executors.newFixedThreadPool(3);
    private static ScheduledExecutorService sh = new ScheduledThreadPoolExecutor(1);


    public static void main(String[] args) {
        Account a = new Account(1000);
        Account b = new Account(2000);
        sh.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(a.getFailCount() + "   " + b.getFailCount());
            }
        }, 0, 2, TimeUnit.SECONDS);
        Random random = new Random();
        ArrayList<Future<Boolean>> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add(service.submit(new Transfer(a, b, random.nextInt(200), 1)));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(a.getBallans() + "  " + b.getBallans());
        }
        Transfer.latch.countDown();
        System.out.println(Transfer.latch);


        service.shutdown();
        try {
            service.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Future f : list) {

            try {
                System.out.println(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(a.getBallans() + "  " + b.getBallans());
        sh.shutdownNow();
    }
}
