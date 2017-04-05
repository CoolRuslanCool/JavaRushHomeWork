package com.javarush.task.task30.task3004;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Created by ruslan on 25.03.17.
 */
public class BinaryRepresentationTask extends RecursiveTask<String> {
    private int x;

    public BinaryRepresentationTask(int x) {
        this.x = x;
    }

    @Override
    protected String compute() {
        int a = x%2;
        int b = x/2;
        String res = String.valueOf(a);
        List<BinaryRepresentationTask> list = new LinkedList<>();
        if (b>0) {
            BinaryRepresentationTask task = new BinaryRepresentationTask(b);
            task.fork();
            list.add(task);
        }
        for (BinaryRepresentationTask task: list)
            res = task.join() + res;

        return res;
    }
}
