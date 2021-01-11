package com.readutf.uLib.libraries.chatmenu;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;

@AllArgsConstructor
public abstract class ChatMenu {

    public abstract void onChat(Player player, String message);
}
