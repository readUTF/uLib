package com.readutf.uLib.libraries.chatmenu;

import com.readutf.uLib.ULib;
import com.readutf.uLib.libraries.SpigotUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class ChatMenuManager implements Listener {

    private static ChatMenuManager chatMenuManager;

    HashMap<ChatMenu, Player> chatMenus;

    public ChatMenuManager() {
        chatMenus = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(this, ULib.getPlugin());
    }

    public static ChatMenuManager get() {
        if (chatMenuManager == null) {
            return new ChatMenuManager();
        } else {
            return chatMenuManager;
        }
    }


    public void openChatMenu(Player player, ChatMenu chatMenu) {
        chatMenus.put(chatMenu, player);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        AtomicReference<ChatMenu> chatMenu1 = new AtomicReference<>();

        List<ChatMenu> toRemove = new ArrayList<>();
        for (ChatMenu chatMenu : chatMenus.keySet()) {
            if (e.getPlayer() == chatMenus.get(chatMenu)) {
                chatMenu1.set(chatMenu);
                e.setCancelled(true);
                if (e.getMessage().equalsIgnoreCase("cancel")) {
                    toRemove.add(chatMenu);
                    e.getPlayer().sendMessage(SpigotUtils.color("&cCanceled."));
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.DIG_GRASS, 20F, 0.1F);

                    continue;
                }
                chatMenu.onChat(e.getPlayer(), e.getMessage());
            }
        }

        toRemove.forEach(chatMenu -> chatMenus.remove(chatMenu));
        if (chatMenu1.get() != null) {
            chatMenus.remove(chatMenu1.get());
        }
    }

}
