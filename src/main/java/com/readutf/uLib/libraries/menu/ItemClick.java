package com.readutf.uLib.libraries.menu;

import lombok.Getter;
import org.bukkit.entity.Player;

public abstract class ItemClick {

    @Getter int id;

    public ItemClick(int id) {
        this.id = id;
    }

    public abstract void itemClick(Player player);

}
