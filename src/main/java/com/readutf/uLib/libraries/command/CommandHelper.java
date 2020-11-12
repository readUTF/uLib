package com.readutf.uLib.libraries.command;

import com.readutf.uLib.libraries.SpigotUtils;
import lombok.Getter;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandHelper {

    String command;
    String description;
    List<SubCommand> subCommands;
    ChatColor primary;
    ChatColor secondary;
    ChatColor tertiary;

    public CommandHelper(String command, String description, ChatColor primary, ChatColor secondary, ChatColor tertiary, List<SubCommand> subCommands) {
        this.command = command;
        this.description = description;
        this.primary = primary;
        this.secondary = secondary;
        this.tertiary = tertiary;
        this.subCommands = subCommands;
    }

    public void handleSubs(CommandSender player, String[] args) {
        for (SubCommand sub : subCommands) {

            if (sub.getName().equalsIgnoreCase(args[0]) || sub.getAliases().contains(args[0])) {
                if (sub.playerOnly) {
                    if (!(player instanceof Player)) {
                        player.sendMessage(SpigotUtils.color("&cYou must be a player to use this command."));
                        return;
                    }
                }
                if (player.hasPermission(sub.getPermission())) {
                    if (args.length >= sub.requiredLength) {
                        sub.execute(player, command, args);
                        return;
                    } else {
                        player.sendMessage(SpigotUtils.color("&cUsage: /" + command + " " + sub.getName() +" " + sub.getUsage()));
                        return;
                    }
                } else {
                    player.sendMessage(SpigotUtils.color("&cYou do not have permission to use this command."));
                    return;
                }
            }
        }
        sendTo(player);
    }

    public void sendTo(CommandSender sender) {
        sender.sendMessage(primary + ChatColor.STRIKETHROUGH.toString() + "-----------------------------------------------------");
        sender.sendMessage(primary + ChatColor.BOLD.toString() + WordUtils.capitalize(command) + " Help " + ChatColor.RESET + tertiary + "- " + ChatColor.WHITE + description);
        sender.sendMessage(primary + ChatColor.STRIKETHROUGH.toString() + "-----------------------------------------------------");
        sender.sendMessage(primary + WordUtils.capitalize(command) + " commands");
        for (SubCommand sub : subCommands) {
            sender.sendMessage(secondary + "/" + command + " " + sub.getName() + " " + sub.getUsage() + tertiary + " - " + sub.getDescription());
        }
        sender.sendMessage(primary + ChatColor.STRIKETHROUGH.toString() + "-----------------------------------------------------");
    }

    public enum LANG {



        NO_PERM("&cYou do not have permission to use this command"),
        PLAYER_ONLY("&cYou must be a player to use this command"),
        PLAYER_NOT_FOUND("&cCould not find player '{1}."),
        INVALID_INT("&c{1} is not a valid number.");

        LANG(String text) {
            this.text = text;
        }

        @Getter String text;

    }

}
