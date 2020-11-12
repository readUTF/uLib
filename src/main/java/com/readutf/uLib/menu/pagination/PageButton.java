package com.readutf.uLib.menu.pagination;

import com.readutf.uLib.libraries.ItemBuilder;
import com.readutf.uLib.menu.Button;
import lombok.AllArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

@AllArgsConstructor
public class PageButton extends Button {

	private int mod;
	private PaginatedMenu menu;

	@Override
	public ItemStack getButtonItem(Player player) {
		if (this.mod > 0) {
			if (hasNext(player)) {
				return new ItemBuilder(Material.REDSTONE_TORCH_ON)
						.setName(ChatColor.GREEN + "Next Page")
						.setLore(Arrays.asList(
								ChatColor.YELLOW + "Click here to jump",
								ChatColor.YELLOW + "to the next page."
						))
						.toItemStack();
			} else {
				return new ItemBuilder(Material.LEVER)
						.setName(ChatColor.GRAY + "Next Page")
						.setLore(Arrays.asList(
								ChatColor.YELLOW + "There is no available",
								ChatColor.YELLOW + "next page."
						))
						.toItemStack();
			}
		} else {
			if (hasPrevious(player)) {
				return new ItemBuilder(Material.REDSTONE_TORCH_ON)
						.setName(ChatColor.GREEN + "Previous Page")
						.setLore(Arrays.asList(
								ChatColor.YELLOW + "Click here to jump",
								ChatColor.YELLOW + "to the previous page."
						))
						.toItemStack();
			} else {
				return new ItemBuilder(Material.LEVER)
						.setName(ChatColor.GRAY + "Previous Page")
						.setLore(Arrays.asList(
								ChatColor.YELLOW + "There is no available",
								ChatColor.YELLOW + "previous page."
						))
						.toItemStack();
			}
		}
	}

	@Override
	public void clicked(Player player, ClickType clickType) {
		if (this.mod > 0) {
			if (hasNext(player)) {
				this.menu.modPage(player, this.mod);
				Button.playNeutral(player);
			} else {
				Button.playFail(player);
			}
		} else {
			if (hasPrevious(player)) {
				this.menu.modPage(player, this.mod);
				Button.playNeutral(player);
			} else {
				Button.playFail(player);
			}
		}
	}

	private boolean hasNext(Player player) {
		int pg = this.menu.getPage() + this.mod;
		return this.menu.getPages(player) >= pg;
	}

	private boolean hasPrevious(Player player) {
		int pg = this.menu.getPage() + this.mod;
		return pg > 0;
	}

}
