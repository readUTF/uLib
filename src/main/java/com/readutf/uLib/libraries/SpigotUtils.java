package com.readutf.uLib.libraries;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpigotUtils {

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static ArrayList<String> color(List<String> array) {
        ArrayList<String> coloured = new ArrayList<>();

        array.forEach(s -> coloured.add(color(s)));
        return coloured;
    }

    public static ArrayList<String> replaceList(List<String> array, String oldChar, String newChar) {
        ArrayList<String> replaced = new ArrayList<>();
        array.forEach(s -> replaced.add(s.replace(oldChar, newChar)));
        return replaced;
    }

    public static int roundToNine(int x) {
        if(x < 9) {
            return 9;
        }
        if(x < 18) {
            return 18;
        }
        if(x < 27) {
            return 27;
        }
        if(x < 36) {
            return 36;
        }
        if(x < 45) {
            return 45;
        }
        return 54;
    }

    public static String formatLocation(Location l) {
        if (l == null)
            return "None";

        return l.getBlockX() + ", " + l.getBlockY() + ", " + l.getBlockZ();
    }

    public static String formatLocation(Location l, String errorReturn) {
        if (l == null)
            return errorReturn;

        return l.getBlockX() + ", " + l.getBlockY() + ", " + l.getBlockZ();
    }

    public static Integer parseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return null;
        }
    }

    public static UUID getFromMojang(String name) {
        try {
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = reader.readLine();

            if (line == null) {
                return null;
            }

            String[] id = line.split(",");


            String part = id[1];
            part = part.substring(6, 38);

            return UUID.fromString(part.replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5"));
        } catch (Throwable e) {
            return null;
        }
    }


}
