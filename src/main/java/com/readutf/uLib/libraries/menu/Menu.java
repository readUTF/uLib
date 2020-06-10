package com.readutf.uLib.libraries.menu;

import com.readutf.uLib.libraries.menu.button.Button;
import com.readutf.uLib.libraries.menu.utils.RandomColors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Menu implements Listener {

    Inventory inv;
    ArrayList<Player> players = new ArrayList<>();
    HashMap<ItemStack, ItemClick> events = new HashMap<>();
    boolean exact;
    boolean update;

    public Menu(int rows, String name, boolean exact) {
        inv = Bukkit.createInventory(null, rows * 9, ChatColor.translateAlternateColorCodes('&', getName(name)));
        this.exact = exact;
    }
    public Menu(int rows, String name, boolean exact, boolean update) {
        inv = Bukkit.createInventory(null, rows * 9, ChatColor.translateAlternateColorCodes('&', getName(name)));
        this.exact = exact;
        this.update = update;
    }

    public String getName(String name) {
        String newname = name + RandomColors.getColour();
        if(newname.length() >= 32) {
            return name;
        }
        return newname;
    }

    public void openMenu(Player player) {
        player.openInventory(inv);
        players.add(player);
    }

    public Menu setItem(int slot, ItemStack itemStack) {
        inv.setItem(slot, itemStack);
        return this;
    }

    public void refresh(Player player) {
        for(int x = 0; x < inv.getContents().length; x++) {
            try {
                player.getOpenInventory().setItem(x, inv.getContents()[x]);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public Menu setItem(int slot, ChangingItemStack itemStack) {
        inv.setItem(slot, itemStack.getItem());
        return this;
    }

    public Menu setItem(int slot, ItemStack itemStack, ItemClick click) {
        inv.setItem(slot, itemStack);
        events.put(itemStack, click);
        return this;
    }

    public Menu setButton(int slot, Button button) {
        inv.setItem(slot, button.getItem());
        events.put(button.getItem(), button.itemClick());
        return this;
    }

    public boolean isMenu(Inventory inv) {
        return inv.getName().equalsIgnoreCase(this.inv.getName());
    }

//    @EventHandler
//    public void onClick(InventoryClickEvent e) {
//        Player player = (Player) e.getWhoClicked();
//        if(e.getCurrentItem() == null) {
//            return;
//        }
//        if(e.getInventory() == inv) {
//            if(players.contains(player)) {
//                e.setCancelled(true);
//                for(ItemStack itemStack : events.keySet()) {
//                    if(!e.getCurrentItem().isSimilar(itemStack)) {
//                        continue;
//                    }
//                    ItemClick click = events.get(itemStack);
//                    click.itemClick(player);
//                    return;
//                }
//            }
//        }
//    }

}
