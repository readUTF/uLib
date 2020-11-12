package com.readutf.uLib.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ButtonBuilder {

    public static Button itemToButton(ItemStack itemStack) {
        return new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return itemStack;
            }
        };
    }
    public static Button itemToButton(ItemStack itemStack, Runnable runnable) {
        return new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                return itemStack;
            }

            @Override
            public void clicked(Player player, ClickType clickType) {
                runnable.run();
            }
        };
    }

}
