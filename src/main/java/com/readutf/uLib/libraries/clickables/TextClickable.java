package com.readutf.uLib.libraries.clickables;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class TextClickable implements Listener {

    private static TextClickable textClickable;

    HashMap<String, Runnable> clicks = new HashMap<>();

    public TextClickable(JavaPlugin javaPlugin) {
        Bukkit.getPluginManager().registerEvents(this, javaPlugin);
        textClickable = this;
    }

    public String getCommand(Runnable runnable) {
        String key = UUID.randomUUID().toString();
        clicks.put(key, runnable);
        return "/textclickable " + key;
    }

    public static TextClickable get() {
        return textClickable;
    }

    @EventHandler
    public void onChat(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().split(" ")[0].equalsIgnoreCase("/textclickable")) {
            e.setCancelled(true);
            for (String s : clicks.keySet()) {
                if(e.getMessage().split(" ")[1].equalsIgnoreCase(s)) {
                    clicks.get(s).run();
                }
            }
        }
    }

}
