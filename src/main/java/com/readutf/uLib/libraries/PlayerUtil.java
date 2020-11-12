package com.readutf.uLib.libraries;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerUtil {



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
