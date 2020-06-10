package com.readutf.uLib.libraries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColorArray {

    List<String> list;

    public ColorArray(List<String> list) {
        this.list = list;
    }

    public ColorArray(ArrayList<String> list) {
        this.list = list;
    }

    public ColorArray(String[] list) {
        this.list = Arrays.asList(list);
    }

    public void replace(String from, String to) {
        List<String> newList = new ArrayList<String>();
        for (String s : list) {
            newList.add(s.replace(from, to));
        }
        list = newList;
    }

    public List<String> build() {
        return list;
    }


}
