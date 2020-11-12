package com.readutf.uLib.menu.pagination.templates;

import com.readutf.uLib.libraries.ItemBuilder;
import com.readutf.uLib.libraries.SpigotUtils;
import com.readutf.uLib.menu.Button;
import com.readutf.uLib.menu.ButtonBuilder;
import com.readutf.uLib.menu.Menu;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ConfirmMenu extends Menu {

    String title;
    Runnable execute;

    public ConfirmMenu(String title, Runnable execute) {
        this.title = title;
        this.execute = execute;

    }

    @Override
    public String getTitle(Player player) {
        return title;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        HashMap<Integer, Button> buttons = new HashMap<>();

        buttons.put(11, ButtonBuilder.itemToButton(
                new ItemBuilder(Material.EMERALD_BLOCK).setName(SpigotUtils.color("&aConfirm")).toItemStack(),
                () -> {execute.run();player.closeInventory();}));

        buttons.put(15, ButtonBuilder.itemToButton(
                new ItemBuilder(Material.REDSTONE_BLOCK).setName(SpigotUtils.color("&cCancel")).toItemStack(),
                player::closeInventory));

        buttons.put(26, ButtonBuilder.itemToButton(
                new ItemBuilder(Material.AIR).toItemStack()
        ));

        return buttons;
    }


}
