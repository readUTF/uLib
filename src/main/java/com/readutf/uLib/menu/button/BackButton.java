package com.readutf.uLib.menu.button;

import java.util.Arrays;

import com.readutf.uLib.libraries.ItemBuilder;
import com.readutf.uLib.menu.Button;
import com.readutf.uLib.menu.Menu;
import lombok.AllArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class BackButton extends Button {

	private Menu back;

	@Override
	public ItemStack getButtonItem(Player player) {
		return new ItemBuilder(Material.REDSTONE)
				.setName(ChatColor.RED.toString() + ChatColor.BOLD.toString() + "Back")
				.setLore(Arrays.asList(
						ChatColor.RED + "Click here to return to",
						ChatColor.RED + "the previous menu.")
				)
				.toItemStack();
	}

	@Override
	public void clicked(Player player, ClickType clickType) {
		Button.playNeutral(player);
		back.openMenu(player);
	}

}
