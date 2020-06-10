package com.readutf.uLib.libraries.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class UInventory implements Listener {

    JavaPlugin plugin;
    HashMap<String, Menu> menuHashMap = new HashMap<>();

    public UInventory(JavaPlugin plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);


        new BukkitRunnable() {

            @Override
            public void run() {
                for(Menu menu : menuHashMap.values()) {
                    if(menu.update) {
                        for(Player player : menu.players) {
                            menu.refresh(player);
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);


    }


    public Menu createMenu(String name, Menu menu) {
        if(menuHashMap.containsKey(name)) {
            return menuHashMap.get(name);
        }
        menuHashMap.put(name, menu);
        return menu;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getInventory() == null || e.getCurrentItem() == null) {
            return;
        }

        LinkedList<Menu> list = new LinkedList<Menu>(menuHashMap.values());

        for(Menu menu : list) {
            if(menu.isMenu(e.getInventory())) {
                if(menu.players.contains((Player) e.getWhoClicked())) {
                    e.setCancelled(true);
                    for(ItemStack itemStack : menu.events.keySet()) {
                        if(menu.exact && !e.getCurrentItem().isSimilar(itemStack)) {
                            continue;
                        } else if(e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getItemMeta().getDisplayName() != null && !e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemStack.getItemMeta().getDisplayName())) {
                            continue;
                        }
                        ItemClick click = menu.events.get(itemStack);
                        if(click == null) {
                            continue;
                        }
                        click.itemClick((Player) e.getWhoClicked());
                        return;
                    }
                }
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if(e.getInventory() == null) {
            return;
        }

        LinkedList<Menu> list = new LinkedList<Menu>(menuHashMap.values());

        for(Menu menu : list) {
            if(menu.isMenu(e.getInventory())) {
                menuHashMap.remove(menu);
            }
        }
    }

}
