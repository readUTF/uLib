package com.readutf.uLib.libraries;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class Players {

    public static List<Player> getOnlinePlayers() {
        return Lists.newArrayList(Bukkit.getServer().getOnlinePlayers());
    }

}
