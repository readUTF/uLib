package com.readutf.uLib.libraries.clickables;

import com.readutf.uLib.menu.EntityClick;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Clickable implements Listener {

    @Getter ItemStack item;
    @Getter int type;
    @Getter
    ItemClick click;
    @Getter
    EntityClick entityClick;
    @Getter List<Action> actions;
    boolean isRegistered = false;

    public Clickable(ItemStack itemStack, ItemClick click, List<Action> actions) {
        this.item = itemStack;
        this.click = click;
        this.actions = actions;
        type = 0;
    }

    public Clickable(ItemStack item, EntityClick click) {
        this.item = item;
        this.entityClick = click;
        type = 1;
    }

    public void givePlayer(Player player, int slot) {
        player.getInventory().setItem(slot, item);
    }



}
