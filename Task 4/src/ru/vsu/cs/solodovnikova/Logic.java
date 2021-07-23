package ru.vsu.cs.solodovnikova;

import java.util.Arrays;

public class Logic {

    public static int[] sortUp(int[] a, SortState sort) {
        sort.createArrayStates(Arrays.copyOf(a,a.length));
        sort.createArrayJI(new int[]{0, 0});
        int time = 0;
        for (int j = 0; j < a.length; j++) {
            for (int i = j + 1; i < a.length; i++) {
                if (a[j] > a[i]) {
                    time = a[i];
                    a[i] = a[j];
                    a[j] = time;
                }
                sort.createArrayStates(Arrays.copyOf(a,a.length));
                sort.createArrayJI(new int[]{j, i});
            }
        }
        return a;
    }

    public static int[] sortDown(int[] a, SortState sort) {
        sort.createArrayStates(Arrays.copyOf(a,a.length));
        sort.createArrayJI(new int[]{0, 0});
        int time = 0;
        for (int j=0; j < a.length; j++) {
            for (int i = j + 1; i < a.length; i++) {
                if (a[j] < a[i]) {
                    time = a[i];
                    a[i] = a[j];
                    a[j] = time;
                }
                sort.createArrayStates(Arrays.copyOf(a,a.length));
                sort.createArrayJI(new int[]{j, i});
            }
        }
        return a;
    }
}

