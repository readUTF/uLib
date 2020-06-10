package com.readutf.uLib.libraries;

import lombok.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public abstract class EntityClick {

    @Getter
    int id;

    public EntityClick(int id) {
        this.id = id;
    }

    public abstract void entityClick(Player player, Entity clicked);

}
