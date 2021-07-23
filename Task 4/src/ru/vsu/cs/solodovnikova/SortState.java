package ru.vsu.cs.solodovnikova;

import java.util.ArrayList;
import java.util.List;

public class SortState {
    public List<int[]> list = new ArrayList<>();
    public List<int[]> ji = new ArrayList<>();

    public void createArrayStates(int[] a) {
        list.add(a);
    }

    public void createArrayJI(int[] a){
        ji.add(a);
    }

}
