package com.readutf.uLib.libraries.menu.button.buttons;

import com.readutf.uLib.libraries.ItemBuilder;
import com.readutf.uLib.libraries.menu.ItemClick;
import com.readutf.uLib.libraries.menu.button.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CloseButton  extends Button {


    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.IRON_DOOR).setName("&cExit.").setLore("&7Click to close the inventory.").toItemStack();
    }

    @Override
    public ItemClick itemClick() {
        return new ItemClick(1) {
            @Override
            public void itemClick(Player player) {
                player.closeInventory();;
            }
        };
    }
}
