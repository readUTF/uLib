package com.readutf.uLib.menu;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;

@AllArgsConstructor
public abstract class ChatMenu {

    public abstract void onChat(Player player, String message);
}
