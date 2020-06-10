package com.readutf.uLib.libraries.menu.utils;

import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomColors {

    HashMap<Integer, ChatColor> colors = new HashMap<>();


    public static String getColour() {
        Random random = new Random();
        StringBuilder str = new StringBuilder();
        IntStream.rangeClosed(0, 6).forEach(x -> {
            str.append(ChatColor.values()[random.nextInt(ChatColor.values().length)].toString());
        });
        return str.toString();
    }

}
