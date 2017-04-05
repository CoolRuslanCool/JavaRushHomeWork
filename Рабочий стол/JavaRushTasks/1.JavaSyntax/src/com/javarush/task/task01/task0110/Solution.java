package com.javarush.task.task01.task0110;

/* 
На нашем экране — переменная
*/

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Solution {
    public static void main(String[] args) {

        Date date = new Date(new SimpleDateFormat("dd.MMM.yyyy").format(new Date()));
        System.out.println(date);
//        float f = 3.1233141f;
//        System.out.println(String.format("%.2f", f).replace(',', '.'));
//        List<List<Integer>> lists = getPowerSet(new ArrayList<>(Arrays.asList(1,2,3)))
    }


//    public void quickSort(int[] mas, int n) {
//        int i = 0, j = n - 1;
//        int tmp, p;
//        p = mas[n>>1];
//        do {
//            while (mas[i] < p) i++;
//            while (mas[j] > p) j--;
//            if (i <= j) {
//                tmp = mas[i];
//                mas[i] = mas[j];
//                mas[j] = tmp;
//                i++;
//                j--;
//            }
//        } while (i<=j);
//        if (j>0) quickSort(mas, j);
//        if (n>i) quickSort(mas, n - i);
//    }

    public static <T> List<List<T>> getPowerSet(List<T> set, int n) {
        List<List<T>> powerSet;
        if (n == set.size()) {
            powerSet = new ArrayList<>();
            powerSet.add(new ArrayList<>());
        } else {
            powerSet = getPowerSet(set, n + 1);
            T item = set.get(n);
            List<List<T>> moreSet = new ArrayList<>();
            for (List<T> li : powerSet) {
                List<T> newSet = new ArrayList<>();
                newSet.addAll(li);
                newSet.add(item);
                moreSet.add(newSet);
            }
            powerSet.addAll(moreSet);
        }
        return powerSet;
    }
}
/*
public List<AdvertType<Advertisement>> getPowerSet(List<Advertisement> set, int n) {
        List<AdvertType<Advertisement>> powerSet;
        if (n == set.size()) {
            powerSet = new ArrayList<>();
            powerSet.add(new AdvertType());
        } else {
            powerSet = getPowerSet(set, n + 1);
            Advertisement item = set.get(n);
            List<AdvertType<Advertisement>> moreSet = new ArrayList<>();
            for (List<Advertisement> li : powerSet) {
                AdvertType<Advertisement> newSet = new AdvertType(li);
                newSet.add(item);
                moreSet.add(newSet);
            }
            powerSet.addAll(moreSet);
        }
        return powerSet;
    }
 */
