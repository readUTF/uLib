package com.readutf.uLib.libraries.clickables;

import com.readutf.uLib.ULib;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClickableManager implements Listener {

    JavaPlugin plugin;
    private static ClickableManager clickableManager;

    public static boolean registered = false;

    HashMap<Player, ArrayList<Clickable>> clickables;

    public ClickableManager(JavaPlugin plugin) {
        this.plugin = plugin;
        clickables = new HashMap<>();
        clickableManager = this;
        register();
    }

    public void register() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void giveClickable(Player player, Clickable clickable, int slot) {
        ArrayList<Clickable> clicks = clickables.getOrDefault(player, new ArrayList<>());
        clicks.add(clickable);
        clickables.put(player, clicks);

        player.getInventory().setItem(slot, clickable.getItem());
    }
    public void registerClickable(Player player, Clickable clickable, int slot) {
        ArrayList<Clickable> clicks = clickables.getOrDefault(player, new ArrayList<>());
        clicks.add(clickable);
        clickables.put(player, clicks);
    }



    public void clear(Player player) {
        clickables.put(player, new ArrayList<>());
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ArrayList<Clickable> clicks = clickables.getOrDefault(player, new ArrayList<>());

        try {
            AtomicBoolean stop = new AtomicBoolean(false);
            clicks.forEach(clickable -> {
                if (clickable.type == 0) {
                    if (e.getItem() == null) return;
                    if (e.getItem().isSimilar(clickable.item)) {
                        clickable.getActions().forEach(action -> {
                            if (!stop.get()) {
                                if (e.getAction() == action) {
                                    clickable.getClick().itemClick(e.getPlayer());
                                    e.setCancelled(true);
                                    stop.set(true);
                                }
                            }
                        });
                    }
                }
            });
        } catch (ConcurrentModificationException ex) {

        }
    }

    @EventHandler
    public void onClickPlayer(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();
        ArrayList<Clickable> clicks = clickables.getOrDefault(player, new ArrayList<>());

        try {

            ULib.verbose(this.getClass(), "1");
            AtomicBoolean stop = new AtomicBoolean(false);

            clicks.forEach(clickable -> {

                if (clickable.type == 1) {
                    if (e.getPlayer().getItemInHand() == null) return;
                    if (e.getPlayer().getItemInHand().isSimilar(clickable.item)) {

                        if (!stop.get()) {
                            clickable.getEntityClick().entityClick(player, e.getRightClicked());
                            e.setCancelled(true);
                            stop.set(true);
                        }

                    }
                }
            });
        } catch (ConcurrentModificationException ex) {

        }
    }

    public static ClickableManager get() {
        return clickableManager;
    }

}
