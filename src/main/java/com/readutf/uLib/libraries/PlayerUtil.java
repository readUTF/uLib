package com.readutf.uLib.libraries;

import com.readutf.uLib.libraries.menu.Menu;
import com.readutf.uLib.libraries.menu.UInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;

public class PlayerUtil {

    public static Menu inspectPlayer(Player open, PlayerInventory inv, int health) {
        Menu menu = new Menu(6,ColorUtil.color("&7Inventory Inspect."), true);
        for(int x = 0; x < 9; x++) {
            inv.setItem(x + 27, inv.getItem(x));
        }
        for(int x = 9; x < 36; x++) {
            inv.setItem(x - 9, inv.getItem(x));
        }

        for(int x = 0; x < 9; x++) {
            inv.setItem(36 + x, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 7).toItemStack());
        }

        menu.setItem(45, inv.getHelmet());
        menu.setItem(46, inv.getChestplate());
        menu.setItem(47, inv.getLeggings());
        menu.setItem(48, inv.getBoots());

        ArrayList<String> playerInfoLore = new ArrayList<String>();
        menu.setItem(53, new ItemBuilder(Material.BOOK).setName(ColorUtil.color("&7Player Info")).setLore(playerInfoLore).toItemStack());


        return menu;

    }

    public static void clear(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(new ItemStack[]{});
    }

    public static double getHealth(Player player) {
        try {
            return (double)player.getClass().getMethod("getHealth", double.class).invoke(player);
        } catch (Exception e) {
            return 0;
        }
    }

}
